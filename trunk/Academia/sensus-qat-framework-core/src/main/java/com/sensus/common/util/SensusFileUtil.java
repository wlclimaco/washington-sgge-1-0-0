package com.sensus.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class QATFileUtil.
 * <b>Note, this class is currently for internal use only by other QAT-Framework classes.</b>
 */
public final class SensusFileUtil
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SensusFileUtil.class);

	/** The Constant BUFFER. */
	private static final int BUFFER = 4096;

	/**
	 * Interface used to determine if a given file should be included in processing.
	 */
	public interface IFileFilter
	{
		/**
		 * The file being passed in should be evaluated and based on the return code the file will
		 * be processed or not.
		 * 
		 * @param file The file
		 * @return true if the file should be processed.
		 */
		public boolean accept(File file);
	}

	/**
	 * COPY file.
	 * 
	 * @param fromFile the from file
	 * @param toFile the to file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void copyFile(File fromFile, File toFile) throws IOException
	{
		FileUtils.copyFile(fromFile, toFile);

	}

	/**
	 * List files.
	 * 
	 * @param sourceFile the source file
	 * @param extensions the extensions
	 * @param recursive the recursive
	 * @return the collection
	 */
	@SuppressWarnings("unchecked")
	public static Collection<File> listFiles(File sourceFile, String[] extensions, boolean recursive)
	{
		return FileUtils.listFiles(sourceFile, extensions, recursive);
	}

	/**
	 * List files.
	 * 
	 * @param sourceFile the source file
	 * @param filter the filter
	 * @param recursive the recursive
	 * @return the collection
	 */
	@SuppressWarnings("unchecked")
	public static Collection<File> listFiles(File sourceFile, final IFileFilter filter, boolean recursive)
	{
		IOFileFilter myFilter = new IOFileFilter()
		{
			@Override
			public boolean accept(File file)
			{
				return filter.accept(file);
			}

			@Override
			public boolean accept(File file, String arg1)
			{
				return filter.accept(file);
			}
		};

		if (recursive == true)
		{
			return FileUtils.listFiles(sourceFile, myFilter, TrueFileFilter.INSTANCE);
		}
		else
		{
			return FileUtils.listFiles(sourceFile, myFilter, null);
		}
	}

	/**
	 * COPY file to directory.
	 * 
	 * @param fromFile the from file
	 * @param toDir the to dir
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void copyFileToDirectory(File fromFile, File toDir) throws IOException
	{
		FileUtils.copyFileToDirectory(fromFile, toDir);
	}

	/**
	 * DELETE.
	 * 
	 * @param deleteFile the delete file
	 * @param recursive the recursive
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void delete(File deleteFile, boolean recursive) throws IOException
	{
		if (deleteFile.isDirectory())
		{
			if (recursive == true)
			{
				FileUtils.forceDelete(deleteFile);
			}
			else
			{
				FileUtils.deleteDirectory(deleteFile);
			}
		}
		else
		{
			FileUtils.forceDelete(deleteFile);
		}
	}

	/**
	 * MOVE to directory.
	 * 
	 * @param fromFile the from file
	 * @param toFile the to file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void moveToDirectory(File fromFile, File toFile) throws IOException
	{
		FileUtils.moveToDirectory(fromFile, toFile, true);
	}

	/**
	 * COPY directory.
	 * 
	 * @param fromFile the from file
	 * @param toFile the to file
	 * @param recursive the recursive
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void copyDirectory(File fromFile, File toFile, boolean recursive) throws IOException
	{
		if (recursive == true)
		{
			FileUtils.copyDirectory(fromFile, toFile);
		}
		else
		{
			FileUtils.copyDirectoryToDirectory(fromFile, toFile);
		}
	}

	/**
	 * CLEAN.
	 * 
	 * @param cleanFile the clean file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void clean(File cleanFile) throws IOException
	{
		FileUtils.cleanDirectory(cleanFile);
	}

	/**
	 * Ungzip file.
	 * 
	 * @param sourceFile the source file
	 * @param removeSource the remove source
	 * @return true, if successful
	 */
	public static boolean ungzipFile(File sourceFile, boolean removeSource)
	{
		String baseFileName = null;

		// Get the base file name
		int dotPos = sourceFile.getName().lastIndexOf(".");
		if (dotPos > 0)
		{
			baseFileName = sourceFile.getName().substring(0, dotPos);
		}

		File outputFile = null;

		outputFile = new File(sourceFile.getParentFile().getAbsoluteFile() + File.separator + baseFileName);

		return ungzipFile(sourceFile, outputFile, removeSource);
	}

	/**
	 * Ungzip file.
	 * 
	 * @param sourceFile the source file
	 * @param outputFile the output file
	 * @param removeSource the remove source
	 * @return true, if successful
	 */
	public static boolean ungzipFile(File sourceFile, File outputFile, boolean removeSource)
	{
		try
		{
			// Open the compressed file
			GZIPInputStream in = new GZIPInputStream(new FileInputStream(sourceFile));

			OutputStream out = new FileOutputStream(outputFile);

			// Transfer bytes from the compressed file to the output file
			byte data[] = new byte[BUFFER];
			int count;
			while ((count = in.read(data)) > 0)
			{
				out.write(data, 0, count);
			}

			// Close the file and stream
			in.close();
			out.close();

			if (removeSource)
			{
				sourceFile.deleteOnExit();
				LOG.info("Deleted source file[" + sourceFile.getAbsolutePath() + "]");
			}
		}
		catch (IOException e)
		{
			LOG.error("Exception encountered uncompressing(gzip) file[" + sourceFile.getAbsolutePath() + "], message="
					+ e.getMessage(), e);
			if (outputFile.exists())
			{
				outputFile.delete();
			}
			return false;
		}
		return true;
	}

	/**
	 * GZIP file.
	 * 
	 * @param sourceFile the source file
	 * @param outputFile the output file
	 * @param removeSource the remove source
	 * @return true, if successful
	 */
	public static boolean gzipFile(File sourceFile, File outputFile, boolean removeSource)
	{
		try
		{
			FileOutputStream dest = new FileOutputStream(outputFile);

			GZIPOutputStream out = new GZIPOutputStream(new BufferedOutputStream(dest));

			byte data[] = new byte[BUFFER];

			FileInputStream fi = new FileInputStream(sourceFile);
			BufferedInputStream origin = new BufferedInputStream(fi, BUFFER);

			int count;
			while ((count = origin.read(data, 0, BUFFER)) != -1)
			{
				out.write(data, 0, count);
			}
			origin.close();

			out.close();

			LOG.info("Compressed file[" + sourceFile.getAbsolutePath() + "] to [" + outputFile.getAbsolutePath()
					+ "]");

			if (removeSource)
			{
				sourceFile.delete();
				LOG.info("Deleted source file[" + sourceFile.getAbsolutePath() + "]");
			}
		}
		catch (Exception e)
		{
			LOG.error("Exception encountered compressing(gzip) file[" + sourceFile.getAbsolutePath() + "], message="
					+ e.getMessage(), e);
			if (outputFile.exists())
			{
				outputFile.delete();
			}
			return false;
		}

		return true;
	}

	/**
	 * UNZIP file.
	 * 
	 * @param sourceFile the source file
	 * @param outputDir the output dir
	 * @param removeSource the remove source
	 * @return true, if successful
	 */
	public static boolean unzipFile(File sourceFile, File outputDir, boolean removeSource)
	{
		File outputFile = null;
		try
		{
			BufferedOutputStream dest = null;
			BufferedInputStream is = null;
			ZipEntry entry;
			ZipFile zipfile = new ZipFile(sourceFile);
			Enumeration<? extends ZipEntry> e = zipfile.entries();
			while (e.hasMoreElements())
			{
				entry = e.nextElement();

				is = new BufferedInputStream(zipfile.getInputStream(entry));
				int count;
				byte data[] = new byte[BUFFER];

				outputFile = new File(outputDir.getName() + File.separator + entry.getName());
				FileOutputStream fos = new FileOutputStream(outputFile);

				dest = new BufferedOutputStream(fos, BUFFER);
				while ((count = is.read(data, 0, BUFFER)) != -1)
				{
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
				is.close();
				LOG.info("Uncompressed file[" + sourceFile.getAbsolutePath() + "] to [" + outputFile.getAbsolutePath()
						+ "]");
			}

			zipfile.close();

			if (removeSource)
			{
				sourceFile.deleteOnExit();
				LOG.info("Deleted source file[" + sourceFile.getAbsolutePath() + "]");
			}
		}
		catch (Exception e)
		{
			LOG.error("Exception encountered compressing(gzip) file[" + sourceFile.getAbsolutePath() + "], message="
					+ e.getMessage(), e);
			if (outputFile.exists())
			{
				outputFile.delete();
			}
			return false;
		}

		return true;
	}

	/**
	 * ZIP file.
	 * 
	 * @param sourceFile the source file
	 * @param outputFile the output file
	 * @param removeSource the remove source
	 * @return true, if successful
	 */
	public static boolean zipFile(File sourceFile, File outputFile, boolean removeSource)
	{
		try
		{
			FileOutputStream dest = new FileOutputStream(outputFile);

			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));

			ZipEntry entry = new ZipEntry(sourceFile.getName());
			out.putNextEntry(entry);

			byte data[] = new byte[BUFFER];

			FileInputStream fi = new FileInputStream(sourceFile);
			BufferedInputStream origin = new BufferedInputStream(fi, BUFFER);

			int count;
			while ((count = origin.read(data, 0, BUFFER)) != -1)
			{
				out.write(data, 0, count);
			}
			origin.close();

			out.close();

			LOG.info("Compressed file[" + sourceFile.getAbsolutePath() + "] to [" + outputFile.getAbsolutePath()
					+ "]");

			if (removeSource)
			{
				sourceFile.delete();
				LOG.info("Deleted source file[" + sourceFile.getAbsolutePath() + "]");
			}
		}
		catch (Exception e)
		{
			LOG.error("Exception encountered compressing(zip) file[" + sourceFile.getAbsolutePath() + "], message="
					+ e.getMessage(), e);
			if (outputFile.exists())
			{
				outputFile.delete();
			}
			return false;
		}

		return true;
	}
}
