public class Laptop {
    private final String manufacturer;
    private final String model;
    private final String color;
    private final String OS;
    private final String resolution;
    private final int screenInches;
    private final String processor;
    private final String GPU;
    private final int RAMAmount;
    private final int HDDAmount;
    private final int SSDAmount;
    private int price;


    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getOS() {
        return OS;
    }

    public String getResolution() {
        return resolution;
    }

    public int getScreenInches() {
        return screenInches;
    }

    public String getProcessor() {
        return processor;
    }

    public String getGPU() {
        return GPU;
    }

    public int getRAMAmount() {
        return RAMAmount;
    }

    public int getHDDAmount() {
        return HDDAmount;
    }

    public int getSSDAmount() {
        return SSDAmount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price > 0) {
            this.price = price;
        }
    }

    public Laptop(String manufacturer, String model, String color, String OS, String resolution, int screenInches,
                  String processor, String GPU, int RAMAmount, int HDDAmount, int SSDAmount, int price) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.OS = OS;
        this.processor = processor;
        this.GPU = GPU;
        this.RAMAmount = Math.max(RAMAmount, 0);
        this.HDDAmount = Math.max(HDDAmount, 0);
        this.SSDAmount = Math.max(SSDAmount, 0);
        this.resolution = resolution;
        this.screenInches = screenInches;
        this.color = color;
        this.price = price;
    }


    @Override
    public String toString() {
        return String.format("%s %s (Цвет: %s, ОС: %s, Разрешение экрана: %s, Диагональ(в дюймах): %d, Процессор: %s, " +
                "Видеокарта: %s, Объём оперативной памяти: %d, Объём HDD: %d, Объём SSD: %d) Цена: %d Р", manufacturer,
                model, color, OS, resolution, screenInches, processor, GPU, RAMAmount, HDDAmount, SSDAmount, price);
    }
}
