package com.qat.samples.complexmo.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.complexmo.model.Zoo;

public class ZooRequest extends Request
{
	private Zoo zoo;

	public Zoo getZoo()
	{
		return zoo;
	}

	public void setZoo(Zoo zoo)
	{
		this.zoo = zoo;
	}

	@Override
	public String toString()
	{
		return "ZooRequest [zoo=" + zoo + ", toString()=" + super.toString() + "]";
	}

}
