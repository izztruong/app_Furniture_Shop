const express = require("express");
const router = express.Router();
const productController = require("../Controller/Product.Controller");
const cloudinary = require("../middleWare/cloudinary.middlewere");

router.get("/product", productController.getProduct);
router.get(
  "/productfollowcategory",
  productController.getProductFollowCategory
);
router.post(
  "/product",
  cloudinary.single("image"),
  productController.addProduct
);
router.delete("/product/:id", productController.deleteProduct);

module.exports = router;
