const express = require("express");
const router = express.Router();
const user = require("../Controller/User.Controller");

router.post("/user/register", user.register);
router.post("/user/login", user.login);

module.exports = router;
