module.exports = (sequelize, DataTypes) => {
  const Cart = sequelize.define("Cart", {});
  Cart.associate = (models) => {
    Cart.belongsTo(models.Product, { foreignKey: "ProductId" });
    Cart.belongsTo(models.User, { foreignKey: "UserId" });
  };

  return Cart;
};
