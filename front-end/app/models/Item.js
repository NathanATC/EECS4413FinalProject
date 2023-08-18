class Item {
  constructor(
    item_iD,
    item_name,
    category,
    description,
    amount_in_stock,
    price,
    futureAvailability,
    imagePath
  ) {
    this.id = item_iD;
    this.name = item_name;
    this.category = category;
    this.description = description;
    this.stock = amount_in_stock;
    this.price = price;
    this.futureAvailability = futureAvailability;
    this.image = imagePath;
  }
}

export default Item;
