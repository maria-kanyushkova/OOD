package lab1;

public class FigureDTO {
    private String inputPath;

    public FigureDTO(String inputPath) {
        setInputPath(inputPath);
    }

    private void setInputPath(String value) {
        inputPath = value;
    }

    public String getInputPath() {
        return inputPath;
    }
}
