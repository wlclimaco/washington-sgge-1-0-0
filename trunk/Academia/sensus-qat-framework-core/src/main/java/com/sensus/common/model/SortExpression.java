package com.sensus.common.model;

import com.sensus.common.model.request.Request;

/**
 * This class encapsulates a sort expression used in conjunction with the {@link Request} object.
 */
public class SortExpression
{

	/**
	 * Used to specify the direction for this sort expression.
	 */
	public static enum Direction
	{

		/** The sort expression is Ascending. */
		Ascending,
		/** The sort expression is Descending. */
		Descending;
	}

	/** The field. */
	private String field;

	/** The direction. */
	private Direction direction = Direction.Ascending;

	/** The priority. */
	private Integer priority;

	/**
	 * Sort expression.
	 */
	public SortExpression()
	{

	}

	/**
	 * Sort expression.
	 *
	 * @param field the field
	 * @param direction the direction
	 */
	public SortExpression(String field, Direction direction)
	{
		setField(field);
		setDirection(direction);
	}

	/**
	 * Render the statement.
	 */
	@Override
	public String toString()
	{
		if (direction == Direction.Ascending)
		{
			return field;
		}
		else
		{
			return field + " DESC";
		}
	}

	/**
	 * Gets the fields used to sort by.
	 *
	 * @return the field
	 */
	public String getField()
	{
		return field;
	}

	/**
	 * Sets the field used to sort by.
	 *
	 * @param field the new field
	 */
	public void setField(String field)
	{
		this.field = field;
	}

	/**
	 * Gets the direction.
	 *
	 * @return the direction
	 */
	public Direction getDirection()
	{
		return direction;
	}

	/**
	 * Gets the string value for the direction for use in SQL.
	 *
	 * @return The direction string value
	 */
	public String getDirectionValue()
	{
		if (direction != Direction.Ascending)
		{
			return "DESC";
		}
		else
		{
			return "ASC";
		}
	}

	/**
	 * Sets the direction.
	 *
	 * @param direction the new direction
	 */
	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}

	/**
	 * Gets the priority for this expression. This comes into play when their are multiple expressions.
	 *
	 * @return the priority
	 */
	public Integer getPriority()
	{
		return priority;
	}

	/**
	 * Sets the priority.
	 *
	 * @param priority the new priority
	 */
	public void setPriority(Integer priority)
	{
		this.priority = priority;
	}
}
