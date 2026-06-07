public class SingletonDemo {

    public static void main(String[] args) {

        AppConfig obj1 = AppConfig.getInstance();
        AppConfig obj2 = AppConfig.getInstance();

        obj1.showMessage();

        System.out.println(obj1 == obj2); // true
    }
}

class AppConfig {

    private static AppConfig instance;

    private AppConfig() {
        System.out.println("Object created only once");
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Singleton object is working");
    }
}