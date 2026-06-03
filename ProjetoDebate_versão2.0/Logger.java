import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static Logger instance;
    private List<String> logs;

    private Logger() { logs = new ArrayList<>(); }

    public static Logger getInstance() {
        if (instance == null) instance = new Logger();
        return instance;
    }

    public void registerLog(String log) { logs.add(log); }

    public void getAllLogs() {
        if (logs.isEmpty()) {
            System.out.println("Nenhum log registrado.");
            return;
        }
        for (String log : logs) System.out.println(log);
    }
}
