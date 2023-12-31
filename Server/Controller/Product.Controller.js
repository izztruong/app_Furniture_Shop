const cloudinary = require("cloudinary").v2;
const { Product, Category } = require("../models");
exports.getProduct = async (req, res) => {
  try {
    const listProduct = await Product.findAll();
    if (listProduct) {
      return res.status(200).json(listProduct);
    } else {
      res.status(400).json({ status: 400, message: "false connexting db" });
    }
  } catch (error) {
    console.log(error);
    return res
      .status(500)
      .json({ status: 500, message: "Internal server error" });
  }
};

exports.getProductFollowCategory = async (req, res) => {
  try {
    const categoryID = req.query.id;
    console.log(categoryID);
    if (!categoryID) {
      return res
        .status(404)
        .json({ status: 404, message: "Category not found" });
    }
    const product = await Product.findAll({
      where: { CategoryId: categoryID },
      raw: true,
    });
    console.log(product);
    if (!product) {
      return res
        .status(404)
        .json({ status: 404, message: "Category not found" });
    }
    return res.status(200).json(product);
  } catch (error) {
    console.log(error);
    return res
      .status(500)
      .json({ status: 500, message: "Internal server error" });
  }
};
exports.addProduct = async (req, res) => {
  try {
    const { name, image, price, description, quantity, CategoryId } = req.body;
    console.log(req.body);
    const filedata = req.file;

    if (!name || (!image && !filedata) || !price || !quantity) {
      if (filedata) {
        // Nếu có lỗi và có tệp ảnh đã tải lên, hủy tệp ảnh trên Cloudinary
        cloudinary.uploader.destroy(filedata.filename);
      }
      return res
        .status(400)
        .json({ status: 400, message: "Fields cannot be left blank" });
    }

    const category = await Category.findByPk(CategoryId); // Đợi cho truy vấn hoàn thành

    console.log("aaaaaaa");
    // Kiểm tra nếu CategoryId đã được xác định
    if (!category) {
      return res.status(400).json({
        status: 400,
        message: "CategoryId is a required field",
      });
    }

    let imageUrl = "";
    if (filedata) {
      // Tiến hành tải lên hình ảnh lên Cloudinary
      const result = await cloudinary.uploader.upload(filedata.path);
      imageUrl = result.secure_url;
    } else if (image) {
      imageUrl = image;
    }

    const product = {
      name,
      image: imageUrl,
      price,
      description,
      quantity,
      CategoryId,
    };

    const addProduct = await Product.create(product);
    console.log(addProduct);
    if (!addProduct) {
      return res
        .status(500)
        .json({ status: 500, message: "Error connecting to database" });
    }

    return res.status(201).json({ status: 201, data: addProduct });
  } catch (error) {
    console.log(error);
    return res
      .status(500)
      .json({ status: 500, message: "Internal server error" });
  }
};

exports.deleteProduct = async (req, res) => {
  const productID = req.params.id;
  const product = Product.findByPk(productID);
  if (!product) {
    res.status(404).json({ status: 404, message: "Product not found" });
  }
  const deleteCategory = await Product.destroy();
  if (deleteCategory) {
    res.status(200).json({ status: 200, message: "delete successfuly" });
  } else {
    res.status(400).json({ status: 400, message: "false connexting db" });
  }
};
