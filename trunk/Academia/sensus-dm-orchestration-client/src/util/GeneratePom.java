package util;

import java.io.File;

public class GeneratePom
{
	public static void main(String[] args)
	{
		String inputDir = "build/packageDir";
		String outputPomDir = "build";

		File currentDir = new File(".");
		System.out.println("current directory is " + currentDir.getAbsolutePath());
		File inputDirFile = new File(inputDir);

		if (inputDirFile.exists())
		{
			if (inputDirFile.isDirectory())
			{
				System.out.println("InputDir found and it is a directory.");
			}
		}
		else
		{
			System.out.println("InputDir NOT found");
				return ;
		}

		File[] files = inputDirFile.listFiles();
		System.out.println("InputDir contains " + files.length + " files.");

		String pomEntry = null ;
		for (File f : files)
		{
			pomEntry = processFile(f);
		System.out.println(pomEntry) ;
		}
		System.out.println("Done.");
	}

	private static String processFile(File f)
	{
		String fileName = f.getName();
		//System.out.println("Processing " + fileName);

		int dash = fileName.indexOf('-');
		char firstCharAfterDash = fileName.charAt(dash + 1);

		for (int i = 0; i < 5; i++)
		{
			if (Character.isDigit(firstCharAfterDash))
			{
				break;
			}

			dash = fileName.indexOf('-', dash + 1);
			firstCharAfterDash = fileName.charAt(dash + 1);
		}

		String jarName = fileName.substring(0, dash);

		int jarExtension = fileName.indexOf(".jar");
		String revision = fileName.substring(dash + 1, jarExtension);

		return generatePomDependency(jarName, revision);
	}

	private static String generatePomDependency(String jarName, String revision)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<dependency>");

		sb.append("<groupId>");

		if (jarName.startsWith("api"))
			sb.append("com.sensus.api");

		else if (jarName.startsWith("bcf"))
			sb.append("flexnet");
		else if ( jarName.startsWith("activem"))
			sb.append("org.apache.activemq");
		else if ( jarName.startsWith("messagetypes"))
			sb.append("com.sensus.api");
		else
			sb.append("sensus");

		sb.append("</groupId>");

		sb.append("<artifactId>");
		sb.append( jarName ) ;
		sb.append("</artifactId>");

		sb.append("<version>");
		sb.append( revision ) ;
		sb.append("</version>");

		sb.append("<type>jar</type>" ) ;
		sb.append("<scope>compile</scope>" ) ;

		sb.append("</dependency>");

		return sb.toString();
	}

	// <dependency>
	// <groupId>flexnet</groupId>
	// <artifactId>nextgenbackfill-constants</artifactId>
	// <version>0.0.0-SNAPSHOT</version>
	// </dependency>

}
