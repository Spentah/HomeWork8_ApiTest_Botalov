package org.example.model;

import com.google.gson.annotations.SerializedName;

public class Order{

	@SerializedName("petId")
	private int petId;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("id")
	private int id;

	@SerializedName("shipDate")
	private String shipDate;

	@SerializedName("complete")
	private boolean complete;

	@SerializedName("status")
	private String status;

	public int getPetId(){
		return petId;
	}

	public int getQuantity(){
		return quantity;
	}

	public int getId(){
		return id;
	}

	public String getShipDate(){
		return shipDate;
	}

	public boolean isComplete(){
		return complete;
	}

	public String getStatus(){
		return status;
	}

	public void setId(int id) {
		this.id = id;
	}



	@Override
	public String toString() {
		return "Order{" +
				"petId=" + petId +
				", quantity=" + quantity +
				", id=" + id +
				", shipDate='" + shipDate + '\'' +
				", complete=" + complete +
				", status='" + status + '\'' +
				'}';
	}
}