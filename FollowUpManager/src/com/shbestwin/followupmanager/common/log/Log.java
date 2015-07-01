package com.shbestwin.followupmanager.common.log;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by jiajing.mo on 14-10-20.
 */
public class Log {
	private static final String DEFAULT_LOG_FILE = "shbestwin";

	private static final String DEFAULT_LOG_PATH = "shbestwin/log";

	private static String logPath = DEFAULT_LOG_PATH;

	private static boolean isLogToFile = true;

	private static boolean isLogToConsole = true;

	private static final Executor THREAD_EXECUTOR = Executors.newSingleThreadExecutor();

	private String mLogFile;

	private static Object LOCK = new Object();

	public Log() {
		this(DEFAULT_LOG_FILE);
	}

	public Log(String logFile) {
		mLogFile = logFile;
	}

	public void i(String tag, String msg) {
		if (isLogToConsole) {
			android.util.Log.i(tag, msg);
		}
		if (isLogToFile) {
			printToFile(tag, msg);
		}
	}

	public void w(String tag, String msg) {
		if (isLogToConsole) {
			android.util.Log.w(tag, msg);
		}
		if (isLogToFile) {
			printToFile(tag, msg);
		}
	}

	public void d(String tag, String msg) {
		if (isLogToConsole) {
			android.util.Log.e(tag, msg);
		}
		if (isLogToFile) {
			printToFile(tag, msg);
		}
	}

	public void e(String tag, String msg) {
		if (isLogToConsole) {
			android.util.Log.e(tag, msg);
		}
		if (isLogToFile) {
			printToFile(tag, msg);
		}
	}

	public void e(String tag, String msg, Throwable tr) {
		if (isLogToConsole) {
			android.util.Log.e(tag, msg, tr);
		}
		if (isLogToFile) {
			printToFile(tag, msg + '\n' + android.util.Log.getStackTraceString(tr));
		}
	}

	private void printToFile(String tag, String msg) {
		if (mLogFile != null) {
			synchronized (LOCK) {
				PrintRunnable runnable = new PrintRunnable(tag, msg);
				THREAD_EXECUTOR.execute(runnable);
			}
		}
	}

	private class PrintRunnable implements Runnable {

		private String text;

		public PrintRunnable(String tag, String msg) {
			text = getTime() + " " + tag + ":" + msg + "\n";
		}

		@Override
		public void run() {
			FileLock flock = null;
			BufferedWriter buffer = null;
			FileOutputStream out = null;
			OutputStreamWriter writer = null;
			try {
				final String logFile = mLogFile;
				if (logFile != null && Environment.isExternalStorageEmulated()) {
					File path = new File(Environment.getExternalStorageDirectory(), logPath);
					boolean file_flag = path.exists();
					if (!file_flag) {
						file_flag = path.mkdirs();
					}
					if (file_flag) {
						File file = new File(path, logFile + "_" + getDate() + ".log");
						out = new FileOutputStream(file, true);
						FileChannel fc = out.getChannel();
						flock = fc.lock();
						writer = new OutputStreamWriter(out);
						buffer = new BufferedWriter(writer);
						buffer.write(text);
						buffer.flush();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (flock != null) {
					try {
						flock.release();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (buffer != null) {
					try {
						buffer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	/**
	 * 是否向文件输出日志
	 *
	 * @param isLogToFile
	 */
	public static void isLogToFile(boolean isLogToFile) {
		Log.isLogToFile = isLogToFile;
	}

	/**
	 * 是否向控制台输出日志
	 *
	 * @param isLogToConsole
	 */
	public static void isLogToConsole(boolean isLogToConsole) {
		Log.isLogToConsole = isLogToConsole;
	}

	/**
	 * 设置日志路径
	 *
	 * @param path
	 */
	public static void setLogPath(String path) {
		Log.logPath = path;
	}

	private static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
		return sdf.format(new Date());
	}

	private static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd", Locale.CHINA);
		return sdf.format(new Date());
	}
}