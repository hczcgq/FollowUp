package com.shbestwin.followupmanager.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.shbestwin.followupmanager.common.log.Log;

public class FileUtils {
	private static String TAG = "FollowUpManager";
	private static Log mLog = new Log("common");

	/**
	 * 获取权限
	 * 
	 * @param permission
	 *            权限
	 * @param path
	 *            路径
	 */
	public static void chmod(String permission, String path) {
		try {
			String command = "chmod " + permission + " " + path;
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断SD卡是否可用
	 */
	public static boolean checkSDCard() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ? true : false;
	}

	/**
	 * 创建目录(不存在时创建)
	 */
	public static File createDir(String dir) {
		File fileDir = new File(dir);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		return fileDir;
	}

	/**
	 * 创建文件(不存在时创建)
	 */
	public static File createFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	// /**
	// * 压缩文件/文件夹
	// */
	// public static void zip(String zipPathName, String zipToPathName) {
	//
	// try {
	// ZipOutputStream out = new ZipOutputStream(new
	// FileOutputStream(zipToPathName));
	// zip(out, new File(zipPathName), "");
	// out.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// private static void zip(ZipOutputStream zos, File file, String base)
	// throws Exception {
	// if (file.isDirectory()) {
	// File[] files = file.listFiles();
	// zos.putNextEntry(new ZipEntry(base + File.separator));
	// base = base.length() == 0 ? "" : base + File.separator;
	// for (int i = 0; i < files.length; i++) {
	// zip(zos, files[i], base + files[i].getName());
	// }
	// } else {
	// zos.putNextEntry(new ZipEntry(base));
	// FileInputStream fis = new FileInputStream(file);
	// int b;
	// while ((b = fis.read()) != -1) {
	// zos.write(b);
	// zos.flush();
	// }
	// fis.close();
	// }
	// }
	//
	/**
	 * 解压zip格式文件
	 */
	public static boolean unzip(InputStream zipStream, String outputDirName) {
		ZipInputStream zis = null;
		FileOutputStream fos = null;
		try {
			File dirFile = createDir(outputDirName);
			zis = new ZipInputStream(zipStream);

			java.util.zip.ZipEntry zipEntry = null;
			while ((zipEntry = zis.getNextEntry()) != null) {
				if (zipEntry.isDirectory()) {
					// 创建目录
					String dirName = zipEntry.getName();
					dirName = dirName.substring(0, dirName.length() - 1);
					createDir(dirFile.getPath() + File.separator + dirName);
				} else {
					// 解压单个文件
					File file = createFile(dirFile.getPath() + File.separator + zipEntry.getName());
					if (file.lastModified() != zipEntry.getTime()) {
						fos = new FileOutputStream(file);
						int c;
						byte[] by = new byte[1024];
						while ((c = zis.read(by)) != -1) {
							fos.write(by, 0, c);
							fos.flush();
						}

						file.setLastModified(zipEntry.getTime());
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (null != zis) {
				try {
					zis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 解压zip格式文件
	 */
	public static boolean unzipWithFilesBack(InputStream zipStream, String outputDirName) {
		ZipInputStream zis = null;
		FileOutputStream fos = null;
		try {
			File dirFile = createDir(outputDirName);
			zis = new ZipInputStream(zipStream);
			java.util.zip.ZipEntry zipEntry = null;
			while ((zipEntry = zis.getNextEntry()) != null) {
				if (zipEntry.isDirectory()) {
					// 创建目录
					String dirName = zipEntry.getName();
					dirName = dirName.substring(0, dirName.length() - 1);
					File fileDir = new File(dirFile.getPath() + File.separator + dirName);
					fileDir.mkdirs();
				} else {
					// 解压单个文件
					File file = createFile(dirFile.getPath() + File.separator + zipEntry.getName());
					fos = new FileOutputStream(file);
					int c;
					byte[] by = new byte[1024];
					while ((c = zis.read(by)) != -1) {
						fos.write(by, 0, c);
						fos.flush();
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (null != zis) {
				try {
					zis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return false;
	}

	public static String createPNG(Bitmap bitmap, String path, String name) {
		try {
			if (checkSDCard()) {
				createDir(path);
				String filepath = path + "/" + name + ".png";
				FileOutputStream fos = null;
				fos = new FileOutputStream(filepath);
				if (null != fos) {
					bitmap.compress(Bitmap.CompressFormat.PNG, 0, fos);
					fos.flush();
					fos.close();
				}
				return filepath;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	public static void deleteAllFiles(String path) {
		File pathFile = new File(path);
		if (pathFile.exists()) {
			File[] files = pathFile.listFiles();
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					try {
						if (files[i].isFile()) {
							files[i].delete();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static boolean deleteDir(String pathDir) {
		File dir = new File(pathDir);
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(pathDir + "/" + children[i]);
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}

	public static boolean copyFileToDir(String srcFile, String destDir) {

		File inFile = new File(srcFile);
		File outDir = new File(destDir);
		if (inFile.exists() && !inFile.isDirectory()) {
			if (!outDir.exists()) {
				outDir.mkdirs();
			}
			FileInputStream fin = null;
			FileOutputStream fout = null;
			try {
				fin = new FileInputStream(inFile);
				File fileOut = new File(destDir + File.separator + inFile.getName());
				if (fileOut.exists()) {
					fileOut.delete();
					fileOut.createNewFile();
				}
				fout = new FileOutputStream(fileOut);
				int c;
				byte[] b = new byte[1024];
				while ((c = fin.read(b)) != -1) {
					fout.write(b, 0, c);
				}
				fout.flush();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fin != null) {
						fin.close();
					}
					if (fout != null) {
						fout.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean copyFile(InputStream inputStream, String destFile) {

		File outFile = new File(destFile);
		FileOutputStream fout = null;
		try {
			if (outFile.exists()) {
				outFile.delete();
				outFile.createNewFile();
			}
			fout = new FileOutputStream(outFile);
			int c;
			byte[] b = new byte[1024];
			while ((c = inputStream.read(b)) != -1) {
				fout.write(b, 0, c);
			}
			fout.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (fout != null) {
					fout.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static void mergeFile(Context c, String[] partFileList, String destFile) {
		File file = new File(destFile);
		// 把所有小文件都进行写操作后才关闭输出流，这样就会合并为一个文件了
		if (!file.exists()) {// 判断是否存在该文件
			OutputStream out = null;
			InputStream in = null;
			try {
				out = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int readLen = 0;
				for (int i = 0; i < partFileList.length; i++) {
					// 获得输入流 ,注意文件的路径
					in = c.getAssets().open(partFileList[i]);
					while ((readLen = in.read(buffer)) != -1) {
						out.write(buffer, 0, readLen);
					}
					out.flush();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 解压到指定目录
	 * 
	 * @param zipPath
	 * @param descDir
	 * @author isea533
	 */
	public static boolean unZipFiles(String zipPath, String descDir) throws IOException {
		return unZipFiles(new File(zipPath), descDir);
	}

	/**
	 * 解压文件到指定目录
	 * 
	 * @param zipFile
	 * @param descDir
	 * @author isea533
	 */
	@SuppressWarnings("rawtypes")
	public static boolean unZipFiles(File zipFile, String descDir) {
		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}

		InputStream in = null;
		OutputStream out = null;
		try {
			ZipFile zip = new ZipFile(zipFile);
			for (Enumeration entries = zip.getEntries(); entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String zipEntryName = entry.getName();
				in = zip.getInputStream(entry);
				String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");
				// 判断路径是否存在,不存在则创建文件路径
				File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
				if (!file.exists()) {
					file.mkdirs();
				}
				// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
				if (new File(outPath).isDirectory()) {
					continue;
				}
				// 输出文件路径信息
				mLog.i(TAG, "outPath=" + outPath);

				out = new FileOutputStream(outPath);
				byte[] buf1 = new byte[1024];
				int len;
				while ((len = in.read(buf1)) > 0) {
					out.write(buf1, 0, len);
				}
				out.flush();
			}
			mLog.i(TAG, "******************解压完毕********************");
			return true;
		} catch (ZipException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public static String readFile(String filePath) {
		StringBuilder result = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				String lineText = null;
				while ((lineText = bufferedReader.readLine()) != null) {
					result.append(lineText).append("\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result.toString();
	}
}
