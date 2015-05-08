package com.prosperitasglobal.cbof.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;
import com.qat.framework.model.IStringEnum;

/**
 * Utility class for gathering attributes of Enumerators which implement {@link IStringEnum} or {@link IIntegerEnum}
 * into a {@link Map}. The Map structure is as follows:
 *
 * <pre>
 * <code>Map&lt;String, Map&lt;String, EnumDefinition&gt;&gt;</code>
 * </pre>
 *
 * Each Enumerator found will have an entry in this Map. The key is the class name of the Enumerator.
 * The value portion of the Map is another Map which will contain an entry for each value of the Enumerator.
 * The key of this nested Map is a value of the Enumerator and the value nested Map is an {@link EnumDefinition}.
 * For example, using [] brackets around values, an entry for the GroupMemberTypeEnum in Cathodic would contain the
 * following:
 *
 * <pre>
 * com.sensus.cp.group.model.GroupMemberTypeEnum
 *     [1  [GroupMemberTypeEnum  1 GROUP_DIVISION  com.sensus.cp.group.model.groupmembertypeenum.group.division]
 *      2  [GroupMemberTypeEnum  2 GROUP_ZONE      com.sensus.cp.group.model.groupmembertypeenum.group.zone]
 *      3  [GroupMemberTypeEnum  3 GROUP_OTHER     com.sensus.cp.group.model.groupmembertypeenum.group.other]
 *      4  [GroupMemberTypeEnum  4 TAG             com.sensus.cp.group.model.groupmembertypeenum.group.tag]
 *      10 [GroupMemberTypeEnum 10 TEST_POINT      com.sensus.cp.group.model.groupmembertypeenum.test.point]
 *      11 [GroupMemberTypeEnum 11 RECTIFIER       com.sensus.cp.group.model.groupmembertypeenum.rectifier]]
 * </pre>
 *
 * The packageNameArray property or packageName property are used to set the packages to be searched for Enumerators.
 * One of these properties should be injected using Spring. The packageNameArray property takes precedence so that if
 * it is set, the packageName property is ignored. Use the packageNameArray property when there are multiple packages
 * to be searched and the packages are not sub-packages under another package. For example, if all packages in under
 * com.sensus.cp are to be searched, the packageName property can be used:
 *
 * <pre>
 * &lt;bean id="enumCollector" class="com.sensus.common.util.EnumDefinitionCollector"&gt;
 *   &lt;property name="packageName" value="com.sensus.cp" /&gt;
 * &lt;/bean&gt;
 * </pre>
 *
 * However, if packages from com.sensus.cp and com.sensus.dm are to be searched and you don't want to incur the
 * overhead of searching com.sensus, the packageNameArray property can be used:
 *
 * <pre>
 * &lt;bean id="enumCollector" class="com.sensus.common.util.EnumDefinitionCollector"&gt;
 *   &lt;property name="packageNameArray" &gt;
 *     &lt;array value-type="java.lang.String"&gt;
 *       &lt;value>com.sensus.cp&lt;/value&gt;
 *       &lt;value>com.sensus.dm&lt;/value&gt;
 *     &lt;/array>
 *   &lt;/property &gt;
 * &lt;/bean&gt;
 * </pre>
 */
public class EnumDefinitionCollector implements TypeFilter
{
	private Map<String, Map<String, EnumDefinition>> enumDefinitionsMaps;

	/**
	 * The base package name(s) to be scanned for the enums.
	 * Looks for packageNameArray property first.
	 */
	private String[] packageNameArray; // injected by Spring
	/**
	 * Uses packageName property only if packageNameArray not set.
	 */
	private String packageName; // injected by Spring

	/**
	 * Gets the package name to be searched.
	 *
	 * @return the package name
	 */
	public String getPackageName()
	{
		return packageName;
	}

	/**
	 * Sets the package name to be searched.
	 *
	 * @param packageName the new package name
	 */
	public void setPackageName(String packageName)
	{
		this.packageName = packageName;
	}

	/**
	 * Gets the package names to be searched.
	 *
	 * @return the array of package names to be searched
	 */
	public String[] getPackageNameArray()
	{
		return packageNameArray;
	}

	/**
	 * Sets the package names to be searched.
	 *
	 * @param packageNameArray the array of package names
	 */
	public void setPackageNameArray(String[] packageNameArray)
	{
		this.packageNameArray = packageNameArray;
	}

	public EnumDefinitionCollector()
	{

	}

	/**
	 * Populates a Map with EnumDefinition values representing all the values for the Enumerator class whose
	 * name is the key to the outer Map.
	 *
	 * @return a Map containing a sub-Map
	 * @throws Exception
	 */
	public Map<String, Map<String, EnumDefinition>> getEnumDefinitionsMap() throws Exception
	{
		if (enumDefinitionsMaps == null)
		{
			enumDefinitionsMaps = new HashMap<String, Map<String, EnumDefinition>>();
			buildEnumDefinitions();
		}

		return enumDefinitionsMaps;
	}

	/**
	 * This is the controlling method and will check first for a packageNameArray property.
	 * If one does not exist it uses the packageName property.
	 *
	 * @throws Exception
	 */
	private void buildEnumDefinitions() throws Exception
	{
		if (getPackageNameArray() == null)
		{
			setPackageNameArray(new String[] {getPackageName()});
		}

		for (String packageName : getPackageNameArray())
		{
			// This is needed because the match method which is a callback needs the package name
			setPackageName(packageName);

			Set<Class<?>> enumClassSet = scanClassPath(packageName, this, null);

			processSet(enumClassSet);
		}
	}

	/**
	 *
	 * @param set - a Set of either IIntegerEnum Enumerators or IStringEnum Enumerators
	 * @throws Exception
	 */
	private void processSet(Set<Class<?>> set) throws Exception
	{
		Iterator<Class<?>> iterator = set.iterator();
		Class<?> enumerationClass = null;
		Map<String, EnumDefinition> enumDefinitionsMap = null;

		while (iterator.hasNext())
		{
			enumerationClass = iterator.next();
			enumDefinitionsMap = processEnumeration(enumerationClass);
			if (enumDefinitionsMap != null)
			{
				enumDefinitionsMaps.put(enumerationClass.getName(), enumDefinitionsMap);
			}
		}
	}

	/**
	 * Creates the sub-Map containing all values for the enumerationClass parameter.
	 *
	 * @param enumerationClass an Enumerator Class
	 * @return a Map of values for the enumerationClass
	 * @throws Exception
	 */
	private Map<String, EnumDefinition> processEnumeration(Class<?> enumerationClass) throws Exception
	{
		EnumDefinition ed;
		Map<String, EnumDefinition> enumDefinitionsMap = null;

		if (enumerationClass.getEnumConstants() != null)
		{
			enumDefinitionsMap = new HashMap<String, EnumDefinition>();
			for (final Object enumConstant : enumerationClass.getEnumConstants())
			{
				ed = processEnumerationConstant(enumerationClass, enumConstant);
				enumDefinitionsMap.put(ed.getValue(), ed);
			}
		}

		return enumDefinitionsMap;
	}

	/**
	 * Retrieves the simple class name, value, label, i18n key for one value of the enumerationClass.
	 *
	 * @param enumerationClass - the Enumerator containing the values
	 * @param enumConstant - one of the Enumerator constants or values
	 * @return an instantiated EnumDefinition
	 * @throws Exception
	 */
	private EnumDefinition processEnumerationConstant(Class<?> enumerationClass, Object enumConstant) throws Exception
	{
		Object enumValue =
				enumerationClass.getMethod("getValue", new Class<?>[] {}).invoke(enumConstant, new Object[] {});

		Object i18nLabelKey = null;
		if (II18nEnum.class.isAssignableFrom(enumerationClass))
		{
			i18nLabelKey =
					enumerationClass.getMethod("getLabelKey", new Class<?>[] {}).invoke(enumConstant,
							new Object[] {});
		}

		return new EnumDefinition(enumerationClass.getSimpleName(), enumValue.toString(), enumConstant.toString(),
				i18nLabelKey);
	}

	/**
	 *
	 * @param basePackage the packageName to be scanned
	 * @param includeFilter see the match method as this class implements the TypeFilter
	 * @param excludeFilter any classes not to be scanned - not used
	 * @return a Set of Classes which were on the classpath in the basePackage and matched the includeFilter
	 * @throws Exception
	 */
	private Set<Class<?>> scanClassPath(String basePackage,
			TypeFilter includeFilter, TypeFilter excludeFilter)
					throws Exception
					{
		Set<Class<?>> result = new HashSet<Class<?>>();
		ResourcePatternResolver resourcePatternResolver =
				new PathMatchingResourcePatternResolver();
		String packageSearchPath =
				ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX;
		if (basePackage != null)
		{
			packageSearchPath += ClassUtils.convertClassNameToResourcePath(basePackage);
		}
		packageSearchPath += "/**/*.class";

		MetadataReaderFactory metadataReaderFactory =
				new CachingMetadataReaderFactory(
						resourcePatternResolver);

		Resource[] resources = resourcePatternResolver
				.getResources(packageSearchPath);

		Class<?> clazz;

		for (Resource resource : resources)
		{
			MetadataReader metadataReader = metadataReaderFactory
					.getMetadataReader(resource);
			ClassMetadata classMetadata =
					metadataReader.getClassMetadata();

			if ((includeFilter != null)
					&& !includeFilter.match(metadataReader,
							metadataReaderFactory))
			{
				continue;
			}

			if ((excludeFilter != null)
					&& excludeFilter.match(metadataReader,
							metadataReaderFactory))
			{
				continue;
			}

			clazz = Class.forName(classMetadata.getClassName());
			result.add(clazz);
		}
		return result;
					}

	@Override
	/**
	 * A match is any class which is an Enum and implements IStringEnum or IIntegerEnum
	 *
	 * @param metadataReader, metadataReaderFactory
	 * @return boolean for matches
	 */
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metatdataReaderFactory)
			throws IOException

	{
		String[] interfaces = metadataReader.getClassMetadata().getInterfaceNames();
		if ((interfaces == null) || (interfaces.length == 0))
		{
			return false;
		}

		String className = metadataReader.getClassMetadata().getClassName();
		if (!className.startsWith(getPackageName()))
		{
			return false;
		}

		Class<?> clazz = null;
		try
		{
			clazz = Class.forName(className);
		}
		catch (ClassNotFoundException e)
		{
			throw new IOException(e);
		}

		if (!clazz.isEnum())
		{
			return false;
		}

		if (IStringEnum.class.isAssignableFrom(clazz))
		{
			return true;
		}
		else if (IIntegerEnum.class.isAssignableFrom(clazz))
		{
			return true;
		}

		return false;
	}

	/**
	 * The EnumDefinition inner class is a composition of attributes. Each EnumDefinition is meant to correspond
	 * to a value of an IStringEnum or IIntegerEnum Enumerator. The attributes it contains are:
	 *
	 * <pre>
	 * typeName: the simple class name of the Enumerator containing the value
	 * value: the value itself
	 * label: the Enumerator label for the value
	 * i18nLabelKey: the key to a localized label name
	 * </pre>
	 */
	public static class EnumDefinition
	{
		private String typeName;
		private String value;
		private String label;
		private String i18nLabelKey;

		public EnumDefinition(String simpleName, String enumValue, String enumLabel, Object labelKey)
		{
			typeName = simpleName;
			value = enumValue;
			label = enumLabel;
			i18nLabelKey = labelKey == null ? null : labelKey.toString();
		}

		public String getTypeName()
		{
			return typeName;
		}

		public void setTypeName(String typeName)
		{
			this.typeName = typeName;
		}

		public String getValue()
		{
			return value;
		}

		public void setValue(String value)
		{
			this.value = value;
		}

		public String getLabel()
		{
			return label;
		}

		public void setLabel(String label)
		{
			this.label = label;
		}

		public String getI18nLabelKey()
		{
			return i18nLabelKey;
		}

		public void setI18nLabelKey(String i18nLabelKey)
		{
			this.i18nLabelKey = i18nLabelKey;
		}

		@Override
		public String toString()
		{
			return "EnumDefinition [getTypeName()=" + getTypeName() + ", getValue()=" + getValue() + ", getLabel()="
					+ getLabel() + ", getI18nLabelKey()=" + getI18nLabelKey() + "]";
		}
	}

}
