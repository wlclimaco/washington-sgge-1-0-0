import com.sensus.common.model.SensusModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Dominios.
 */
public class Dominios extends SensusModel
{

    /** The nmtabela. */
    private String nmtabela;

    /** The nmatribu. */
    private String nmatribu;

    /** The dsdomini. */
    private String dsdomini;

    /** The vrdomini. */
    private String vrdomini;

	/**
	 * Gets the nmtabela.
	 *
	 * @return the nmtabela
	 */
	public String getNmtabela() {
		return nmtabela;
	}

	/**
	 * Sets the nmtabela.
	 *
	 * @param nmtabela the new nmtabela
	 */
	public void setNmtabela(String nmtabela) {
		this.nmtabela = nmtabela;
	}

	/**
	 * Gets the nmatribu.
	 *
	 * @return the nmatribu
	 */
	public String getNmatribu() {
		return nmatribu;
	}

	/**
	 * Sets the nmatribu.
	 *
	 * @param nmatribu the new nmatribu
	 */
	public void setNmatribu(String nmatribu) {
		this.nmatribu = nmatribu;
	}

	/**
	 * Gets the dsdomini.
	 *
	 * @return the dsdomini
	 */
	public String getDsdomini() {
		return dsdomini;
	}

	/**
	 * Sets the dsdomini.
	 *
	 * @param dsdomini the new dsdomini
	 */
	public void setDsdomini(String dsdomini) {
		this.dsdomini = dsdomini;
	}

	/**
	 * Gets the vrdomini.
	 *
	 * @return the vrdomini
	 */
	public String getVrdomini() {
		return vrdomini;
	}

	/**
	 * Sets the vrdomini.
	 *
	 * @param vrdomini the new vrdomini
	 */
	public void setVrdomini(String vrdomini) {
		this.vrdomini = vrdomini;
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString() {
		return "Dominios [nmtabela=" + nmtabela + ", nmatribu=" + nmatribu
				+ ", dsdomini=" + dsdomini + ", vrdomini=" + vrdomini + "]";
	}

	/**
	 * Instantiates a new dominios.
	 *
	 * @param nmtabela the nmtabela
	 * @param nmatribu the nmatribu
	 * @param dsdomini the dsdomini
	 * @param vrdomini the vrdomini
	 */
	public Dominios(String nmtabela, String nmatribu, String dsdomini,
			String vrdomini) {
		super();
		this.nmtabela = nmtabela;
		this.nmatribu = nmatribu;
		this.dsdomini = dsdomini;
		this.vrdomini = vrdomini;
	}

}
