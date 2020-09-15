package lab1;

import lab1.common.IShape;
import lab1.common.Point;
import lab1.common.ShapeFactory;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class EventLoop {
    private final Controller controller;

    EventLoop(Controller controller) {
        this.controller = controller;
    }

    private static String getMenuInfo() {
        return "0. help - выводится информация о командах\n" +
                "1. LINE: P1=<startX>,<startY>; P2=<endX>,<endY>; <outlineColor: #000000>\n" +
                "2. TRIANGLE: P1=<vertex1X>,<vertex1Y>; P2=<vertex2X>,<vertex2Y>; P3=<vertex3X>,<vertex3Y>; <outlineColor: #000000> <fillColor: #000000>\n" +
                "3. RECTANGLE: P1=<leftTopX>,<leftTopY>; P2=<rightBottomX>,<rightBottomY>; <outlineColor: #000000> <fillColor: #000000>\n" +
                "4. CIRCLE: C=<centerX>,<centerY>; R=<radius>; <outlineColor: #000000> <fillColor: #000000>\n" +
                "5. draw - рисование введённых фигур\n" +
                "6. exit - выход с приложения";
    }

    private static String readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static String[] getParams(String[] commands) { // todo: написать парсер строки, чтобы в массив шли чисто цифры
        ArrayList<String> result = new ArrayList<>();
        String[] params = commands[1].split("; ");
        String[] tempCoords;
        switch (commands[0]) {
            case "LINE":
                tempCoords = getPointCoords(params[0]);
                result.add(tempCoords[0]);
                result.add(tempCoords[1]);
                tempCoords = getPointCoords(params[1]);
                result.add(tempCoords[0]);
                result.add(tempCoords[1]);
                result.add(params[2]);
                break;
            case "TRIANGLE":
                tempCoords = getPointCoords(params[0]);
                result.add(tempCoords[0]);
                result.add(tempCoords[1]);
                tempCoords = getPointCoords(params[1]);
                result.add(tempCoords[0]);
                result.add(tempCoords[1]);
                tempCoords = getPointCoords(params[2]);
                result.add(tempCoords[0]);
                result.add(tempCoords[1]);
                result.add(params[3]);
                result.add(params[4]);
                break;
            case "RECTANGLE":
                tempCoords = getPointCoords(params[0]);
                result.add(tempCoords[0]);
                result.add(tempCoords[1]);
                tempCoords = getPointCoords(params[1]);
                result.add(tempCoords[0]);
                result.add(tempCoords[1]);
                result.add(params[2]);
                result.add(params[3]);
                break;
            case "CIRCLE":
                tempCoords = getPointCoords(params[0]);
                result.add(tempCoords[0]);
                result.add(tempCoords[1]);
                result.add(params[1].split("=")[1]);
                result.add(params[2]);
                result.add(params[3]);
                break;
            default:
                return null;
        }
        String[] itemsArray = new String[result.size()];
        return result.toArray(itemsArray);
    }

    private static String[] getPointCoords(String param) {
        String coordsString = param.split("=")[1];
        return coordsString.split(",");
    }

    public void run() {
        System.out.println(getMenuInfo());
        while (true) {
            try {
                final String consoleLine = readFromConsole();
                if (runImpl(consoleLine)) {
                    break;
                }
            } catch (Exception error) {
                System.out.println(error.getLocalizedMessage());
            }
        }
    }

    public void run(File inputFile) {
        try (
                FileReader fileReader = new FileReader(inputFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            String commandLine;
            while ((commandLine = bufferedReader.readLine()) != null) {
                runImpl(commandLine);
            }
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }

    private boolean runImpl(String commandsLine) throws Exception {
        final String[] commands = commandsLine.split(": ");
        String result = runCommand(commands);
        if (result.equals("exit")) {
            return true;
        }
        if (result.length() != 0) {
            System.out.println(result);
        }
        return false;
    }

    private String runCommand(String[] args) throws Exception {
        String[] params = getParams(args);
        String command = "";
        if (args.length != 0) {
            command = args[0];
        }
        switch (command) {
            case "help":
                return getMenuInfo();
            case "LINE":
            case "TRIANGLE":
            case "RECTANGLE":
            case "CIRCLE":
                controller.appendShape(createShape(command, params));
                break;
            case "draw":
                break;
            case "exit":
                return "exit";
            default:
                throw new IOException("Встречена незвестная команда");
        }
        return "";
    }

    private IShape createShape(String type, String[] params) throws Exception {
        Color outlineColor;
        Color fillColor;
        switch (type) {
            case "LINE" -> {
                if (params.length != 5) {
                    throw new IOException("Недостаточно аргументов");
                }
                Point start = Utils.convertToPoint(params[0], params[1]);
                Point end = Utils.convertToPoint(params[2], params[3]);
                outlineColor = Utils.convertToColor(params[4]);
                return ShapeFactory.createLine(start, end, outlineColor);
            }
            case "TRIANGLE" -> {
                if (params.length != 8) {
                    throw new IOException("Недостаточно аргументов");
                }
                Point vertex1 = Utils.convertToPoint(params[0], params[1]);
                Point vertex2 = Utils.convertToPoint(params[2], params[3]);
                Point vertex3 = Utils.convertToPoint(params[4], params[5]);
                outlineColor = Utils.convertToColor(params[6]);
                fillColor = Utils.convertToColor(params[7]);
                return ShapeFactory.createTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
            }
            case "RECTANGLE" -> {
                if (params.length != 6) {
                    throw new IOException("Недостаточно аргументов");
                }
                Point leftTop = Utils.convertToPoint(params[0], params[1]);
                Point rightBottom = Utils.convertToPoint(params[2], params[3]);
                outlineColor = Utils.convertToColor(params[4]);
                fillColor = Utils.convertToColor(params[5]);
                return ShapeFactory.createRectangle(leftTop, rightBottom, outlineColor, fillColor);
            }
            case "CIRCLE" -> {
                if (params.length != 5) {
                    throw new IOException("Недостаточно аргументов");
                }
                Point center = Utils.convertToPoint(params[0], params[1]);
                int radius = Utils.convertToNumber(params[2]);
                outlineColor = Utils.convertToColor(params[3]);
                fillColor = Utils.convertToColor(params[4]);
                return ShapeFactory.createCircle(center, radius, outlineColor, fillColor);
            }
            default -> throw new IOException("Unknown shape type");
        }
    }
}
