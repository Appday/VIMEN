package db;

/**
 * Created by Administrator on 29.05.2015.
 */
import android.provider.BaseColumns;

public class TaskContract {
    public static final String DB_NAME = "todolist";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "tasks";

    public class Columns {
        public static final String TASK = "task";
        public static final String _ID = BaseColumns._ID;
    }
}