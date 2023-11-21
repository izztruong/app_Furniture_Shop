const cloudinary = require("cloudinary").v2;
const { CloudinaryStorage } = require("multer-storage-cloudinary");
const multer = require("multer");

cloudinary.config({
  // cloud_name: process.env.CLOUDINARY_NAME,
  // api_key: process.env.CLOUDINARY_KEY,
  // api_secret: process.env.CLOUDINARY_SECRET

  cloud_name: "dkchoy5df",
  api_key: "941256945118745",
  api_secret: "zveE8wUow6XuAOhRhhVwcb-QXjE",
});

const storage = new CloudinaryStorage({
  cloudinary,
  allowedFormats: ["jpg", "png"],
  params: {
    folder: "Furniture_shop",
  },
});

const uploadCloud = multer({ storage });

module.exports = uploadCloud;
