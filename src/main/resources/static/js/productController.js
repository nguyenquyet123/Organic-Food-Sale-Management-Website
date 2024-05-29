app.controller("ProductController", function ($scope, $http) {
  $scope.item = {};
  $scope.items = [];
  $scope.categorys = [];
  $scope.category = {};
  $scope.selectedCategory;
  $scope.item.enteredDate = "2024-01-01";
  $scope.item.image = "";
  $scope.item.description = "";
  $scope.item.status = "";

  
  $scope.reset = () => {
    $scope.item = {};
    $scope.item.image = "";
    $scope.message = "";
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
    $scope.page = $scope.items.slice(begin, end);
  };

  $scope.load_all = () => {
    var url = `${host}/products`;
    $scope.item = {};
    $http
      .get(url)
      .then((resp) => {
        $scope.items = resp.data;
        $scope.totalItems = $scope.items.length;
        $scope.pageChanged();
        console.log("Success", resp);
      })
      .catch((error) => {
        console.log("Error", error);
      });
      $scope.message = "";
  };

  // $scope.edit = (username) => {
  //   var url = `${host}/products/${username}`;
  //   $http
  //     .get(url)
  //     .then((resp) => {
  //       $scope.item = resp.data;
  //       $scope.activeTab = "tab1";
  //       console.log("Success", resp);
  //     })
  //     .catch((error) => {
  //       console.log("Error", error);
  //     });
  //     $scope.message = "";
  // };
  $scope.edit = (product) => {

        $scope.item = product;
        $scope.activeTab = "tab1";
        console.log("Success", resp);

      $scope.message = "";
  };

  $scope.update = () => {
    if ($scope.item.productId == null) {
      $scope.message = "Select the new product to be deleted";
      return;
    }    

    var item = angular.copy($scope.item);
    var url = `${host}/products/${$scope.item.productId}`;
    $http
      .put(url, item)
      .then((resp) => {
        var index = $scope.items.findIndex(
          (item) => item.productId == $scope.item.productId
        );
        $scope.items[index] = resp.data;
        $scope.pageChanged();
        console.log("Success", resp); 
      })
      .catch((error) => {
        console.log("Error", error);
      });
      $scope.message = "";
  };

  $scope.delete = (id) => {
    var url = `${host}/products/${id}`;
    $http
      .delete(url)
      .then((resp) => {
        var index = $scope.items.findIndex(
          (item) => item.productId == id
        );
        $scope.items.splice(index, 1);
        $scope.totalItems = $scope.items.length;
        $scope.message = "DELETE IS SUCCESS";
        $scope.page = $scope.items.slice(0, 2);
        $scope.reset();
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  $scope.create = () => {
    var item = angular.copy($scope.item);
    item.image = "product-6.jpg";
    var url = `${host}/products`;
    $http
      .post(url, item)
      .then((resp) => {
        $scope.items.push(item);
        $scope.totalItems = $scope.items.length;
        $scope.pageChanged();
        $scope.reset();
        console.log("Success", resp);
      })
      .catch((error) => {
        console.log("Error", error);
      });

      $scope.message = "";
  };



  $scope.key = "";
  $scope.message = "";

  $scope.search = (key) => {
    $scope.message = "";
    var item = $scope.items.find((item) => {
      return item.name === key;
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

  $scope.load_categorys = () => {
    var url1 = `${host}/categorys`;
    $scope.item = {};
    $http
      .get(url1)
      .then((resp) => {
        $scope.categorys = resp.data;
        console.log("Success", resp);
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  $scope.load_categorys();
  $scope.load_all();
  $scope.activeTab = "tab1";
});
