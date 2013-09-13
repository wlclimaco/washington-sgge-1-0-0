package com.sensus.common.query;

/**
 * Represents an "Order" to be applied to a given query results set.
 */
public class Order
{

	/**
	 * Used to indicate the direction of a given Order instance.
	 */
	public enum Direction
	{
		ASCENDING, DESCENDING;
	}

	private final String property;
	private Direction direction = Direction.ASCENDING;

	/**
	 * Constructor for creating an Order instance for the given property.
	 *
	 * @param property the property
	 * @param dir the dir
	 */
	public Order(String property, Direction dir)
	{
		this.property = property;
		direction = dir;
	}

	/**
	 * Gets the property.
	 *
	 * @return the property
	 */
	public String getProperty()
	{
		return property;
	}

	/**
	 * Used to render this Order.
	 *
	 * @return the string
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		if (direction != Direction.ASCENDING)
		{
			sb.append(" ORDER BY DESC");
		}

		return sb.toString();
	}

}
