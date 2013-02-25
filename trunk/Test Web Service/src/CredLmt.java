import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "http://barndt.com/services/test")
@XmlAccessorType(XmlAccessType.FIELD)
public class CredLmt
{
	// record
	private String iBFlag;
	private String iBType;
	private int iYIB;
	private int oCredLmt;
	private String oRC;
	private String oRC0;
	private String oRC1;
	private String oRC2;
	private String oRC3;
	private String oRC4;
	private String oRC5;
	private String oRC6;
	private String oRC7;
	private String oRC8;
	private String oRC9;

	public CredLmt()
	{

	}

	public CredLmt(String flag, String type, int iyib, int credLmt, String orc, String orc0, String orc1, String orc2,
			String orc3, String orc4, String orc5, String orc6,
			String orc7, String orc8, String orc9)
	{
		iBFlag = flag;
		iBType = type;
		iYIB = iyib;
		oCredLmt = credLmt;
		oRC = orc;
		oRC0 = orc0;
		oRC1 = orc1;
		oRC2 = orc2;
		oRC3 = orc3;
		oRC4 = orc4;
		oRC5 = orc5;
		oRC6 = orc6;
		oRC7 = orc7;
		oRC8 = orc8;
		oRC9 = orc9;
	}

	public String getIBFlag()
	{
		return iBFlag;
	}

	public void setIBFlag(String flag)
	{
		iBFlag = flag;
	}

	public String getIBType()
	{
		return iBType;
	}

	public void setIBType(String type)
	{
		iBType = type;
	}

	public int getIYIB()
	{
		return iYIB;
	}

	public void setIYIB(int iyib)
	{
		iYIB = iyib;
	}

	public int getOCredLmt()
	{
		return oCredLmt;
	}

	public void setOCredLmt(int credLmt)
	{
		oCredLmt = credLmt;
	}

	public String getORC()
	{
		return oRC;
	}

	public void setORC(String orc)
	{
		oRC = orc;
	}

	public String getORC0()
	{
		return oRC0;
	}

	public void setORC0(String orc0)
	{
		oRC0 = orc0;
	}

	public String getORC1()
	{
		return oRC1;
	}

	public void setORC1(String orc1)
	{
		oRC1 = orc1;
	}

	public String getORC2()
	{
		return oRC2;
	}

	public void setORC2(String orc2)
	{
		oRC2 = orc2;
	}

	public String getORC3()
	{
		return oRC3;
	}

	public void setORC3(String orc3)
	{
		oRC3 = orc3;
	}

	public String getORC4()
	{
		return oRC4;
	}

	public void setORC4(String orc4)
	{
		oRC4 = orc4;
	}

	public String getORC5()
	{
		return oRC5;
	}

	public void setORC5(String orc5)
	{
		oRC5 = orc5;
	}

	public String getORC6()
	{
		return oRC6;
	}

	public void setORC6(String orc6)
	{
		oRC6 = orc6;
	}

	public String getORC7()
	{
		return oRC7;
	}

	public void setORC7(String orc7)
	{
		oRC7 = orc7;
	}

	public String getORC8()
	{
		return oRC8;
	}

	public void setORC8(String orc8)
	{
		oRC8 = orc8;
	}

	public String getORC9()
	{
		return oRC9;
	}

	public void setORC9(String orc9)
	{
		oRC9 = orc9;
	}

}
