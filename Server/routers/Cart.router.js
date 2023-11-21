const express = require("express");
const router = express.Router();
const cartController = require("../Controller/CartController");
const cloudinary = require("../middleWare/cloudinary.middlewere");

router.get("/cart", cartController.getCart);
router.post("/cart/add", cartController.addProducttoCart);
router.post("/cart/delete", cartController.deleteCart);

module.exports = router;
