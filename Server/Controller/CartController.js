const { Cart, Product, User } = require("../models/index");
exports.addProducttoCart = async (req, res) => {
  const { idproduct, iduser } = req.body;
  console.log(idproduct);
  if (!idproduct || !iduser) {
    return res.status(400).json({ message: "Add to cart fail" });
  }
  const cart = await Cart.findAll({
    where: {
      ProductId: idproduct,
      UserId: iduser,
    },
  });
  console.log(cart);
  if (!cart) {
    return res.status(401).json({ message: "Added to cart" });
  }
  const addcart = await Cart.create({
    ProductId: idproduct,
    UserId: iduser,
  });
  if (addcart) {
    res.status(200).json({ message: "Add to cart successfully" });
  } else {
    res.status(400).json({ message: "Add to cart fail" });
  }
};
exports.deleteCart = async (req, res) => {
  const idproduct = req.body.idproduct;
  const iduser = req.body.iduser;
  if (!idproduct || !iduser) {
    return res.status(404).json({ message: "Product not found" });
  }
  const deleteCart = await Cart.destroy({
    where: {
      ProductId: idproduct,
      UserId: iduser,
    },
  });
  if (deleteCart) {
    res.status(200).json({ message: "Delete successfully" });
  } else {
    res.status(400).json({ message: "Delete Fail" });
  }
};
exports.getCart = async (req, res) => {
  const iduser = req.query.id;
  if (!iduser) {
    return res.status(404).json({ message: "Not found id" });
  }
  const listCart = await Cart.findAll({
    where: {
      UserId: iduser,
    },
    include: [
      {
        model: Product,
      },
    ],
  });
  let listProduct = [];
  listCart.forEach((item) => {
    listProduct.push(item.Product);
  });
  //console.log(listProduct);
  res.status(200).json(listProduct);
};
