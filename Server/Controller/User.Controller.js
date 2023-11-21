const { Token } = require("../models");
const { User } = require("../models");
const bcrypt = require("bcrypt");
const SIGN_PRIVATE = "app_furniture_shop";
const jwt = require("jsonwebtoken");
const crypto = require("crypto");
// exports.getUserId = async (req, res) => {
//   try {
//     const token = req.headers.authorization.split(" ")[1];
//     const decoded = jwt.verify(token, SIGN_PRIVATE);
//     console.log(decoded);
//     const user = await User.findOne({
//       where: {
//         id: decoded.id,
//       },
//     });
//     console.log(user);
//     if (user) {
//       return res.status(200).json({
//         status: 200,
//         message: "User found",
//         user: user,
//       });
//     } else {
//       return res.status(404).json({
//         status: 404,
//         message: "User not found",
//       });
//     }
//   } catch (error) {
//     return res.status(401).json({
//       status: 401,
//       message: "Invalid token",
//     });
//   }
// };
exports.register = async (req, res, next) => {
  try {
    console.log(req.body);
    if (!req.body.email || !req.body.password || !req.body.name) {
      return res.status(401).json({ message: "Invalid email, password,name" });
    }

    const user = req.body;
    console.log(user, "a");
    const checkEmail = await User.findOne({
      where: { email: req.body.email },
    });
    console.log(checkEmail);
    if (checkEmail) {
      return res.status(409).json({
        message: "Email already exists ",
      });
    }
    // Tạo salt và mã hóa mật khẩu
    const salt = await bcrypt.genSalt(15);
    user.password = await bcrypt.hash(req.body.password, salt);
    console.log("ok");
    // Lưu tài khoản mới vào cơ sở dữ liệu
    let new_user = await User.create(user);
    console.log("new user", new_user);
    res.status(200).json({
      message: "Sign up successfully",
    });
  } catch (error) {
    console.log(error);
    return res
      .status(500)
      .json({ status: 500, message: "Internal server error" });
  }
};

exports.login = async (req, res, next) => {
  try {
    if (!req.body.email || !req.body.password) {
      return res
        .status(401)
        .json({ status: 401, message: "Email or password is not empty" });
    }

    const result = await User.findOne({
      where: {
        email: req.body.email,
      },
    });
    console.log("result", result);
    if (!result) {
      return res.status(401).json({ status: 401, message: "Not found email" });
    }

    const isPasswordMatch = await bcrypt.compare(
      req.body.password,
      result.password
    );

    if (!isPasswordMatch) {
      return res.status(401).json({ message: "Password is not incorrect" });
    }

    // Đăng nhập thành công
    // Tạo và trả về token

    const account = result;

    // Tạo và lưu token cho người dùng
    console.log("email", account.email);
    const token = jwt.sign(
      { id: account.id, email: account.email },
      SIGN_PRIVATE,
      { expiresIn: "1y" }
    );
    await Token.create({ token: token, userId: result.id });

    return res.status(200).json({
      data: {
        id: account.id,
        email: account.email,
        token: token,
      },
      message: "Login successfully!",
    });
  } catch (error) {
    console.error(error);
    return res.status(500).json({ message: error.message });
  }
};
exports.changPassword = async (req, res, next) => {
  const { newPassword, reNewPassword } = req.body;
  if (newPassword !== reNewPassword) {
    res.status(400).json({
      status: 400,
      message: "Password and rePassword not match",
    });
  }
  try {
    if (!req.user) {
      return res.status(401).json({ status: 401, message: "Unauthorized" });
    }
    const salt = await bcrypt.genSalt(15);
    const changedPassword = await bcrypt.hash(newPassword, salt);
    await Account.update(
      { password: changedPassword },
      {
        where: {
          id: req.user.id,
        },
      }
    );
    return res
      .status(201)
      .json({ status: 201, message: "Change password successfully!" });
  } catch (error) {
    return res.status(500).json({ status: 500, message: error.message });
  }
};
exports.logout = async (req, res, next) => {
  try {
    // Xóa token của người dùng để đăng xuất
    await Token.destroy({
      where: {
        userId: req.body.id,
      },
    });
    return res
      .status(200)
      .json({ status: 200, message: "Logout successfully!" });
  } catch (error) {
    console.error(error);
    return res.status(500).json({ message: error.message });
  }
};
