module.exports = (sequelize, DataTypes) => {
  const User = sequelize.define("User", {
    name: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    password: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    email: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    dateOfBirth: {
      type: DataTypes.STRING,
      allowNull: false,
      defaultValue: "19/09/1990",
    },
    role: {
      type: DataTypes.STRING,
      allowNull: false,
      defaultValue: "User",
    },
  });

  User.associate = (models) => {
    User.hasOne(models.Token, {
      foreignKey: "userId",
    });
    User.hasOne(models.Cart, { foreignKey: "UserId" });
  };
  return User;
};
