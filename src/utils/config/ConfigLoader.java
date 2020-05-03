package utils.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConfigLoader {
    private static ConfigLoader loader;
    private String addressName;
    private HashMap<String, Configs> addressess;
    private static String defaultAddress = "src/assets/configs/MainConfigFiles.properties";
    private HashMap<String, Configs> constants;
    private HashMap<String, Configs> assets;

    private ConfigLoader(String address) {
        initialize(address);
    }

    public static ConfigLoader getInstance() {
        return getInstance("default");
    }

    private void initialize(String address) {
        FileReader reader;
        addressName = "RESOURCE_URL";
        constants = new HashMap<>();
        assets = new HashMap<>();
        addressess = new HashMap<>();
        try {
            Configs addresses = new Configs();
            reader = new FileReader(address);
            addresses.load(reader);
            this.addressess.put(addressName, addresses);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("main config file doesn't exist");
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadProperties();
    }

    private void loadProperties() {
        Set<Map.Entry<Object, Object>> entries = addressess.get("RESOURCE_URL").entrySet();
        for (Map.Entry entry : entries) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        for (Map.Entry<Object, Object> entry : entries) {
            String adrs = (String) entry.getValue();
            String key = (String) entry.getKey();
            String lowerCase = key.toLowerCase();
            if (!lowerCase.contains("url")) {
                Configs property = new Configs();
                try {
                    File test = new File(adrs);
                    System.out.println(test.getAbsolutePath());
                    FileReader reader = new FileReader(test);
                    property.load(reader);

                } catch (FileNotFoundException e) {
                    System.out.println(entry.getKey() + " file doesn't exist");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println(entry.getKey() + " load failed");
                    e.printStackTrace();
                }

                if (lowerCase.contains("asset")) {
                    System.out.println("assets added : " + key);
                    assets.put(key, property);
                } else if (lowerCase.contains("constants")) {
                    System.out.println("constants added : " + key);
                    constants.put(key, property);
                }


            }
        }
        System.out.println("loading finished! ");
    }

    public static ConfigLoader getInstance(String address) {
        if (loader == null) {
            if (address.equals("default")) {
                address = defaultAddress;
            }
            loader = new ConfigLoader(address);
        }
        return loader;
    }

    protected Configs getProperties(String name) {
        System.out.println("in get properties with name : " + name);
        return constants.get(name);

    }

    public String getAddress(String type, String resource_url) {
        return addressess.get(type).getProperty(resource_url);
    }

    public String getAddress(String resource_url) {
        return getAddress(addressName, resource_url);
    }

    protected Configs getAsset(String name) {
        return assets.get(name);
    }

    protected Configs getConstants(String name) {
        return constants.get(name);
    }
}
