const express = require("express");
const app = express();
require("dotenv").config();
//require("./untils/passport");

const bodyParser = require("body-parser");
const userRouter = require("./routers/User.router");
const product = require("./routers/Product.router");
const cart = require("./routers/Cart.router");

const db = require("./models");
const { render } = require("ejs");
const PORT = process.env.POST || 3000;

app.set("view engine", "ejs");
app.use(
  express.static("public", {
    setHeaders: (res, path) => {
      if (path.endsWith(".css")) {
        res.setHeader("Content-Type", "text/css");
      }
    },
  })
);
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(express.json());

// const options = {
//   definition: {
//     openapi: "3.0.0",
//     info: {
//       title: "API APP_Furniture",
//       version: "0.1.0",
//     },
//     servers: [
//       {
//         url: "http://localhost:3000/",
//       },
//     ],
//   },
//   apis: ["./routers/*.js"],
// };
// const spacs = swaggerjsdoc(options);

// app.use("/api-docs", swaggerUi.serve, swaggerUi.setup(spacs));
app.use("/api", cart);
app.use("/api", product);
// app.use("/api", promotion);
// app.use("/api", comment);
app.use("/api", userRouter);
// app.use("/api", accounts_google);
// app.use("/api", address);
// app.use("/api", favorites);

// app.use("/products", productView);
db.sequelize.sync().then(() => {
  app.listen(PORT, () => {
    console.log("server start loacalhost: " + PORT);
  });
});
