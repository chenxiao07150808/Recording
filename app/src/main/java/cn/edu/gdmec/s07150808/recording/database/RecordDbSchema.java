package cn.edu.gdmec.s07150808.recording.database;

/**
 * Created by chen on 2017/3/5.
 */
public class RecordDbSchema {
    public static final class RecordTable{
        public static final String NANE = "records";
        public static final class Cols{
            public static final String UUID ="uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
        }
    }
}
