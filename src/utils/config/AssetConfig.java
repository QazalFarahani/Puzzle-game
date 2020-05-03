package utils.config;

import java.util.ArrayList;

public class AssetConfig {
    private Configs properties;
    private String name;
    private ArrayList<Integer> initArray;
    private String initial_ordering;
    private int missingPiece;

    public AssetConfig(String name) {
        this.name = name;
        setProperties();
        init();
    }

    private void setProperties() {
        this.properties = ConfigLoader.getInstance("default").getAsset(name);
    }

    private void init() {
        initial_ordering = properties.getProperty("initial_ordering");
        missingPiece = properties.readInteger("missingPiece");

        initArray();
    }

    private void initArray() {
        initArray = new ArrayList<>();
        initArray.add(Integer.valueOf(initial_ordering.substring(0, 1)));
        initArray.add(Integer.valueOf(initial_ordering.substring(1, 2)));
        initArray.add(Integer.valueOf(initial_ordering.substring(2, 3)));
        initArray.add(Integer.valueOf(initial_ordering.substring(3, 4)));
        initArray.add(Integer.valueOf(initial_ordering.substring(4, 5)));
        initArray.add(Integer.valueOf(initial_ordering.substring(5, 6)));
        initArray.add(Integer.valueOf(initial_ordering.substring(6, 7)));
        initArray.add(Integer.valueOf(initial_ordering.substring(7, 8)));
        initArray.add(Integer.valueOf(initial_ordering.substring(8, 9)));
    }

    public String getInitial_ordering() {
        return initial_ordering;
    }

    public int getMissingPiece() {
        return missingPiece;
    }

    public ArrayList<Integer> getInitArray() {
        return initArray;
    }
}
