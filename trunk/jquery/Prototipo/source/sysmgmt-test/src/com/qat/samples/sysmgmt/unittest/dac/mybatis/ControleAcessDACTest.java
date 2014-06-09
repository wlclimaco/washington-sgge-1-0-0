package com.qat.samples.sysmgmt.unittest.dac.mybatis;

import java.sql.Date;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.qat.samples.sysmgmt.dac.IControleAcessDAC;
import com.qat.samples.sysmgmt.util.AcaoTypeEnum;
import com.qat.samples.sysmgmt.util.ControleAcess;
import com.qat.samples.sysmgmt.util.TableTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class ControleAcessDACTest extends AbstractTransactionalJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(ControleAcessDACTest.class);
	private IControleAcessDAC controleAcessDAC; // injected by Spring through setter below

	public IControleAcessDAC getControleAcessDAC()
	{
		return controleAcessDAC;
	}

	@Resource
	public void setControleAcessDAC(IControleAcessDAC newValue)
	{
		controleAcessDAC = newValue;
	}

	@Test
	public void testInsertControleAcess() throws Exception
	{
		ControleAcess controleAcess = createControleAcess();
		getControleAcessDAC().insertControleAcess(controleAcess);
		ControleAcess response = getControleAcessDAC().fetchControleAcessById(controleAcess);
		response = getControleAcessDAC().fetchControleAcessByType(controleAcess);

	}

	private ControleAcess createControleAcess()
	{
		Date date = new Date(0);
		ControleAcess controleAcess =
				new ControleAcess(13, date, 1, 1, TableTypeEnum.MARCA, 1, AcaoTypeEnum.INSERT);

		return controleAcess;
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertControle.sql", false);
	}

}
