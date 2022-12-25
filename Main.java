import java.util.*;

public class Main {

    static void printParams(List<String> params) {
        int i = 0;
        for (String param : params) {
            System.out.printf("%d - %s\n", i, param);
            i++;
        }
    }

    static int paramIndexFromUser(Scanner in, int size) {
        int test;
        while (true) {
            while (!in.hasNextInt()) {
                System.out.println("Вы ввели не целое число, попробуйте снова.");
                in.nextLine();
            }
            test = in.nextInt();
            if (test == -1) {
                return -1;
            }
            if (test < 0 || test >= size) {
                System.out.println("Вы ввели некорректное число, попробуйте снова.");
            } else {
                in.nextLine();
                return test;
            }
        }
    }

    static HashMap<String, String> stringMapFromUser(Scanner in) {
        List<String> params = new ArrayList<>(Arrays.asList("Производитель", "Модель", "Цвет", "ОС",
                "Разрешение экрана", "Процессор", "Видеокарта"));
        HashMap<String, String> result = new HashMap<>();
        while (!params.isEmpty()) {
            printParams(params);
            System.out.print("Введите номер соответсвующиё параметру, по которому Вы хотите отбирать ноутбуки" +
                    " или -1, чтобы прекратить: ");
            int paramIndex = paramIndexFromUser(in, params.size());
            if (paramIndex == -1) {
                break;
            }
            System.out.printf("Введите значение, которое должно содержаться в поле %s: ", params.get(paramIndex));
            String paramValue = in.nextLine();
            result.put(params.get(paramIndex), paramValue);
            params.remove(paramIndex);
        }
        return result;
    }

    static int intFromUser(Scanner in) {
        while (!in.hasNextInt()) {
            System.out.println("Вы ввели не целое число, попробуйте снова.");
            in.nextLine();
        }
        return in.nextInt();
    }

    static HashMap<String, Integer> intMapFromUser(Scanner in) {
        List<String> params = new ArrayList<>(Arrays.asList("Диагональ (в дюймах)", "Объём оперативной памяти",
                "Объём HDD", "Объём SSD", "Цена"));
        HashMap<String, Integer> result = new HashMap<>();
        while (!params.isEmpty()) {
            printParams(params);
            System.out.print("Введите число, соответсвующее параметру, по которому вы хотите отбирать ноутбуки" +
                    " или -1, чтобы прекратить: ");
            int paramIndex = paramIndexFromUser(in, params.size());
            if (paramIndex == -1) {
                break;
            }
            System.out.printf("Введите минимальное значение, которое подойдёт вам в поле %s: ", params.get(paramIndex));
            int paramValue = intFromUser(in);
            result.put(params.get(paramIndex), paramValue);
            params.remove(paramIndex);
        }
        return result;
    }

    static boolean fitsParam(Laptop lt, String paramName, String paramValue) {
        switch (paramName) {
            case "Производитель": return lt.getManufacturer().contains(paramValue);
            case "Модель": return lt.getModel().contains(paramValue);
            case "Цвет": return lt.getColor().contains(paramValue);
            case "ОС": return lt.getOS().contains(paramValue);
            case "Разрешение экрана": return lt.getResolution().contains(paramValue);
            case "Процессор": return lt.getProcessor().contains(paramValue);
            case "Видеокарта": return lt.getGPU().contains(paramValue);
            default: return false;
        }
    }

    static boolean fitsParam(Laptop lt, String paramName, int paramValue) {
        switch (paramName) {
            case "Диагональ (в дюймах)": return lt.getScreenInches() >= paramValue;
            case "Объём оперативной памяти": return lt.getRAMAmount() >= paramValue;
            case "Объём HDD": return lt.getHDDAmount() >= paramValue;
            case "Объём SSD": return lt.getSSDAmount() >= paramValue;
            case "Цена": return lt.getPrice() >= paramValue;
            default: return false;
        }
    }

    static Set<Laptop> filter(Set<Laptop> laptopSet, Map<String, String> stringMap, Map<String, Integer> intMap) {
        Set<Laptop> result = new HashSet<>(laptopSet);
        for (String key : stringMap.keySet()) {
            result.removeIf(lt -> !fitsParam(lt, key, stringMap.get(key)));
        }
        for (String key : intMap.keySet()) {
            result.removeIf(lt -> !fitsParam(lt, key, intMap.get(key)));
        }
        return result;
    }

    static void printLtSet(Set<Laptop> ltSet) {
        for (Laptop lt : ltSet) {
            System.out.println(lt.toString());
        }
    }

    public static void main(String[] args) {
        Set<Laptop> ltSet = new HashSet<>();
        ltSet.add(new Laptop("ASUS", "ROG Strix GL503GE", "Black", "Windows 10", "1920x1080", 13,
                "Intel Core I7", "NVIDIA GeForce 1050 Ti", 8, 1024, 128, 70000));
        ltSet.add(new Laptop("Apple", "MacBook Pro", "Silver", "Mac", "6144x3160", 13,
                "M2", "M2", 16, 0, 2048, 120000));
        ltSet.add(new Laptop("Acer", "Swift 5", "Black", "Windows 11", "1920x1080", 13,
                "Intel Core I7", "Intel Iris Xe", 16, 0, 1024, 83499));
        ltSet.add(new Laptop("Acer", "Swift X Intel", "Red", "Windows 11", "1920x1080", 16,
                "Intel Core I7", "NVIDIA GeForce RTX 3050 Ti", 16, 0, 512, 92999));
        printLtSet(ltSet);
        Scanner in = new Scanner(System.in);
        HashMap<String, Integer> intMap = intMapFromUser(in);
        HashMap<String, String> stringMap = stringMapFromUser(in);
        in.close();
        Set<Laptop> result = filter(ltSet, stringMap, intMap);
        printLtSet(result);
    }
}
