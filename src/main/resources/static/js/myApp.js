var app = angular.module("myApp", ["ngRoute"]);

app.config(function ($routeProvider, $httpProvider) {
  $routeProvider
    .when("/", {
      templateUrl: "/spa/home.html",
      controller: "HomeController",
    })

    .when("/chartjs", {
      templateUrl: "/spa/chartjs.html",
      controller: "ChartjsController",
    })

    .when("/authority", {
      templateUrl: "/spa/authority.html",
      controller: "AuthorityController",
    })

    .when("/category", {
      templateUrl: "/spa/category.html",
      controller: "CategoryController",
    })

    .when("/account", {
      templateUrl: "/spa/account.html",
      controller: "AccountController",
    })

    .when("/role", {
      templateUrl: "/spa/role.html",
      controller: "RoleController",
    })

    .when("/product", {
      templateUrl: "/spa/product.html",
      controller: "ProductController",
    })

    .when("/order", {
      templateUrl: "/spa/order.html",
      controller: "OrderController",
    })

    .when("/orderdetail", {
      templateUrl: "/spa/orderdetail.html",
      controller: "OrderDetailController",
    })

    .when("/revenue", {
      templateUrl: "/spa/revenue.html",
      controller: "RevenueController",
    })

    .otherwise({
      redirectTo: "/",
    });

  // var auth = "Basic " + btoa("user:123");
  $httpProvider.defaults.headers.common["Authorization"] =
    "Basic YWRtaW42OjY2Ng==";
});

let host = "http://localhost:8080/rest";

//..............................................................AUTHORITY MANAGER

app.controller("AuthorityController", function ($scope, $http) {
  var url = `${host}/authorities`;

  $http.get(url).then((resp) => {
    $scope.db = resp.data;
    console.log(resp.data);
  });

  $scope.index_of = (username, role) => {
    return $scope.db.authorities.findIndex(
      (a) => a.account.username === username && a.role.roleId === role
    );
  };

  $scope.update = (username, role) => {
    var index = $scope.index_of(username, role);
    if (index >= 0) {
      var id = $scope.db.authorities[index].authorityId;
      $http.delete(`${host}/authorities/${id}`).then((resp) => {
        $scope.db.authorities.splice(index, 1);
      });
    } else {
      var authority = {
        account: { username: username },
        role: { roleId: role },
      };
      $http.post(`${host}/authorities`, authority).then((resp) => {
        $scope.db.authorities.push(resp.data);
      });
    }
  };
});

//..............................................................Accounts MANAGER

app.controller("AccountController", function ($scope, $http) {
  $scope.account = {};
  $scope.accounts = [];
  $scope.reset = () => {
    $scope.account = {};
  };

  $scope.currentPage = 1;
  $scope.itemsPerPage = 2;
  $scope.totalItems = 2;
  $scope.page = {};

  $scope.setPage = (pageNo) => {
    $scope.currentPage = pageNo;
    $scope.pageChanged();
    $scope.message = "";
  };

  $scope.pageChanged = () => {
    var begin = ($scope.currentPage - 1) * $scope.itemsPerPage;
    var end = begin + $scope.itemsPerPage;
    if ($scope.totalItems % 2 !== 0) {
      if ($scope.totalItems < end) {
        var begin = ($scope.currentPage - 1) * $scope.itemsPerPage - 1;
      }
      if ($scope.totalItems === end) {
        $scope.currentPage += 0.5;
      }
    }
    $scope.page = $scope.accounts.slice(begin, end);
  };

  $scope.load_all = () => {
    var url = `${host}/accounts`;
    $scope.account = {};
    $http
      .get(url)
      .then((resp) => {
        $scope.accounts = resp.data;
        $scope.totalItems = $scope.accounts.length;
        $scope.pageChanged();
        console.log("Success", resp);
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  $scope.edit = (username) => {
    var url = `${host}/accounts/${username}`;
    $http
      .get(url)
      .then((resp) => {
        $scope.account = resp.data;
        $scope.activeTab = "tab1";
        console.log("Success", resp);
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  $scope.update = () => {
    var item = angular.copy($scope.account);
    var url = `${host}/accounts/${$scope.account.username}`;
    $http
      .put(url, item)
      .then((resp) => {
        var index = $scope.accounts.findIndex(
          (item) => item.username == $scope.account.username
        );
        $scope.accounts[index] = resp.data;
        $scope.pageChanged();
        console.log("Success", resp);
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  $scope.delete = (username) => {
    var url = `${host}/accounts/${username}`;
    $http
      .delete(url)
      .then((resp) => {
        var index = $scope.accounts.findIndex(
          (item) => item.username == username
        );
        $scope.accounts.splice(index, 1);
        $scope.totalItems = $scope.accounts.length;
        $scope.message = "DELETE IS SUCCESS";
        $scope.page = $scope.accounts.slice(0, 2);
        $scope.reset();
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  $scope.create = () => {
    var item = angular.copy($scope.account);
    var url = `${host}/accounts`;
    $http
      .post(url, item)
      .then((resp) => {
        $scope.accounts.push(item);
        $scope.totalItems = $scope.accounts.length;
        $scope.pageChanged();
        $scope.reset();
        console.log("Success", resp);
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  $scope.key = "";
  $scope.message = "";

  $scope.search = (key) => {
    $scope.message = "";
    var item = $scope.accounts.find((account) => {
      return account.username === key;
    });
    if (item === undefined) {
      $scope.page = [];
      $scope.message = "Username is not empty";
    } else {
      $scope.page = [];
      $scope.page.push(item);
      console.log(item);
      console.log($scope.page);
    }
  };

  $scope.load_all();
  $scope.activeTab = "tab1";
});

//..............................................................ROLE MANAGER

app.controller("RoleController", function ($scope, $http) {
  $scope.role = {};
  $scope.roles = [];
  $scope.reset = () => {
    $scope.role = {};
  };

  $scope.currentPage = 1;
  $scope.itemsPerPage = 2;
  $scope.totalItems = 2;
  $scope.page = {};

  $scope.setPage = (pageNo) => {
    $scope.currentPage = pageNo;
    $scope.pageChanged();
    $scope.message = "";
  };

  $scope.pageChanged = () => {
    var begin = ($scope.currentPage - 1) * $scope.itemsPerPage;
    var end = begin + $scope.itemsPerPage;
    if ($scope.totalItems % 2 !== 0) {
      if ($scope.totalItems < end) {
        var begin = ($scope.currentPage - 1) * $scope.itemsPerPage - 1;
      }
      if ($scope.totalItems === end) {
        $scope.currentPage += 0.5;
      }
    }
    $scope.page = $scope.roles.slice(begin, end);
  };

  $scope.load_all = () => {
    var url = `${host}/roles`;
    $scope.role = {};
    $http
      .get(url)
      .then((resp) => {
        $scope.roles = resp.data;
        $scope.totalItems = $scope.roles.length;
        $scope.pageChanged();
        console.log("Success", resp);
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  $scope.edit = (rolename) => {
    var url = `${host}/roles/${rolename}`;
    $http
      .get(url)
      .then((resp) => {
        $scope.role = resp.data;
        $scope.activeTab = "tab1";
        console.log("Success", resp);
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  $scope.update = () => {
    var item = angular.copy($scope.role);
    var url = `${host}/roles/${$scope.role.roleId}`;
    $http
      .put(url, item)
      .then((resp) => {
        var index = $scope.roles.findIndex(
          (item) => item.roleId == $scope.role.roleId
        );
        $scope.roles[index] = resp.data;
        $scope.pageChanged();
        console.log("Success", resp);
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  $scope.delete = (roleId) => {
    var url = `${host}/roles/${roleId}`;
    $http
      .delete(url)
      .then((resp) => {
        var index = $scope.roles.findIndex((item) => item.roleId == roleId);
        $scope.roles.splice(index, 1);
        $scope.totalItems = $scope.roles.length;
        $scope.message = "DELETE IS SUCCESS";
        $scope.pageChanged();
        $scope.reset();
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  $scope.create = () => {
    var item = angular.copy($scope.role);
    var url = `${host}/roles`;
    $http
      .post(url, item)
      .then((resp) => {
        $scope.roles.push(item);
        $scope.totalItems = $scope.roles.length;
        $scope.page = $scope.roles.slice(0, 2);
        $scope.reset();
        console.log("Success", resp);
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  $scope.key = "";
  $scope.message = "";

  $scope.search = (key) => {
    $scope.message = "";
    var item = $scope.roles.find((role) => {
      return role.roleId === key;
    });
    if (item === undefined) {
      $scope.page = [];
      $scope.message = "Role is not empty";
    } else {
      $scope.page = [];
      $scope.page.push(item);
      console.log(item);
    }
  };

  $scope.load_all();
  $scope.activeTab = "tab1";
});

//..............................................................CART MANAGER

app.controller("CartController", function ($scope, $http, $location) {
  // var url = `${host}/products/${id}`;

  $scope.ids = [];
  $scope.product = {};
  $scope.totalPrice = 0;

  $scope.checkIfProductExists = (obj) => {
    return $scope.ids.some((item) => angular.equals(item, obj));
  };

  $scope.add = (id) => {
    $http
      .get(`/rest/products/${id}`)
      .then((resp) => {
        $scope.product = resp.data;

        if ($scope.checkIfProductExists($scope.product)) {
          return;
        }

        $scope.ids.push($scope.product);

        console.log("Success", resp);
      })
      .catch((error) => {
        console.log("Error", error);
      });

    var message = "Product added to cart successfully!";
    $scope.saveToLocalStorage();
    $scope.showAlert(message);
  };

  $scope.remove = (id) => {
    var index = $scope.ids.findIndex((item) => item.productId == id);
    $scope.ids.splice(index, 1);
    $scope.saveToLocalStorage();
  };

  $scope.total = () => {
    return $scope.ids
      .map((item) => item.unitPrice * item.quantity)
      .reduce((totalPrice, quantity) => (totalPrice += quantity), 0);
  };

  $scope.showAlert = (message) => {
    var alertBox = document.createElement("div");
    alertBox.innerHTML = message;
    alertBox.style.cssText =
      "position:fixed;top:13.5%;right:13.5%;width:20%;border-radius: 10px;background-color:rgba(242, 113, 37,0.7);color:white;text-align:center;padding:20px;z-index:9999;";
    document.body.appendChild(alertBox);

    setTimeout(function () {
      document.body.removeChild(alertBox);
    }, 1500);
  };

  $scope.saveToLocalStorage = () => {
    var json = JSON.stringify(angular.copy($scope.ids));
    localStorage.setItem("cart", json);
  };

  $scope.loadFromLocalStorage = () => {
    var json = localStorage.getItem("cart");
    $scope.ids = json ? JSON.parse(json) : [];
  };

  $scope.orderId = 101;

  $scope.load_orders = () => {
    var url = `${host}/orders`;
    $http
      .get(url)
      .then((resp) => {
        $scope.orderId = resp.data.length;
        console.log("Success", resp);
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  $scope.load_orders();



  $scope.loadFromLocalStorage();
});


app.controller("HomeController", function ($scope) {});

app.controller("ChartjsController", function ($scope) {});
