package lab1;

import lab1.common.IShape;
import lab1.common.Point;
import lab1.common.ShapeFactory;
import lab1.common.decorator.FillDecorator;
import lab1.common.decorator.OutlineDecorator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EventLoop {
    private final ShapeList list;

    public EventLoop(ShapeList list) {
        this.list = list;
    }

    private static String getMenuInfo() {
        return (
            "0. help - выводится информация о командах\n" +
            "1. TRIANGLE: P1=<vertex1X>,<vertex1Y>; P2=<vertex2X>,<vertex2Y>; P3=<vertex3X>,<vertex3Y>; <outlineColor: #000000> <fillColor: #000000>\n" +
            "2. RECTANGLE: P1=<leftTopX>,<leftTopY>; P2=<rightBottomX>,<rightBottomY>; <outlineColor: #000000> <fillColor: #000000>\n" +
            "3. CIRCLE: C=<centerX>,<centerY>; R=<radius>; <outlineColor: #000000> <fillColor: #000000>\n" +
            "4. draw - рисование введённых фигур\n" +
            "5. exit - выход с приложения"
        );
    }

    private static String readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static String[] getParams(String[] commands) {
        ArrayList<String> result = new ArrayList<>();
        String[] params = commands[1].split("; ");
        String[] tempCoords;
        switch (commands[0]) {
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
            case "TRIANGLE":
            case "RECTANGLE":
            case "CIRCLE":
                list.push(createShape(command, params));
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
        switch (type) {
            case "TRIANGLE" -> {
                if (params.length != 8) {
                    throw new IOException("Недостаточно аргументов");
                }
                Point vertex1 = Utils.convertToPoint(params[0], params[1]);
                Point vertex2 = Utils.convertToPoint(params[2], params[3]);
                Point vertex3 = Utils.convertToPoint(params[4], params[5]);

                return applyDecorator(ShapeFactory.createTriangle(vertex1, vertex2, vertex3), params[6], params[7]);
            }
            case "RECTANGLE" -> {
                if (params.length != 6) {
                    throw new IOException("Недостаточно аргументов");
                }
                Point leftTop = Utils.convertToPoint(params[0], params[1]);
                Point rightBottom = Utils.convertToPoint(params[2], params[3]);

                return applyDecorator(ShapeFactory.createRectangle(leftTop, rightBottom), params[4], params[5]);
            }
            case "CIRCLE" -> {
                if (params.length != 5) {
                    throw new IOException("Недостаточно аргументов");
                }
                Point center = Utils.convertToPoint(params[0], params[1]);
                int radius = Utils.convertToNumber(params[2]);

                return applyDecorator(ShapeFactory.createCircle(center, radius), params[3], params[4]);
            }
            default -> throw new IOException("Unknown shape type");
        }
    }

    private IShape applyDecorator(IShape shape, String outlineColor, String fillColor) throws Exception {
        var wrappedShape = shape;

        if (!outlineColor.isEmpty()) {
            wrappedShape = new OutlineDecorator(wrappedShape,  Utils.convertToColor(outlineColor));
        }
        if (!fillColor.isEmpty()) {
            wrappedShape = new FillDecorator(wrappedShape,  Utils.convertToColor(fillColor));
        }

        return wrappedShape;
    }
}
