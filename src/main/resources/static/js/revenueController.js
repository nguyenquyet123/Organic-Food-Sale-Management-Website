app.controller("RevenueController", function ($scope, $http) {
  $scope.item = {};
  $scope.items = [];
  $scope.categorys = [];
  $scope.category = {};
  $scope.total = 0;

  // $scope.currentPage = 1;
  // $scope.itemsPerPage = 2;
  // $scope.totalItems = 2;
  // $scope.page = {};

  // $scope.setPage = (pageNo) => {
  //   $scope.currentPage = pageNo;
  //   $scope.pageChanged();
  //   $scope.message = "";
  // };

  // $scope.pageChanged = () => {
  //   var begin = ($scope.currentPage - 1) * $scope.itemsPerPage;
  //   var end = begin + $scope.itemsPerPage;
  //   if ($scope.totalItems % 2 !== 0) {
  //     if ($scope.totalItems < end) {
  //       var begin = ($scope.currentPage - 1) * $scope.itemsPerPage - 1;
  //     }
  //     if ($scope.totalItems === end) {
  //       $scope.currentPage += 0.5;
  //     }
  //   }
  //   $scope.page = $scope.items.slice(begin, end);
  // };

  $scope.load_all = () => {
    var url = `${host}/orderdetails`;
    $scope.item = {};
    $http
      .get(url)
      .then((resp) => {
        $scope.items = resp.data;
        angular.forEach($scope.items, function (item) {
          $scope.total += item.unitPrice * item.quantity;
        });
        //   $scope.totalItems = $scope.items.length;
        //   $scope.pageChanged();
        console.log("Success", resp);
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  $scope.optionChanged = (name) => {
    var url = `${host}/orderdetails/all/${name}`;
    $http
      .get(url)
      .then((resp) => {
        $scope.items = resp.data;
        angular.forEach($scope.items, function (item) {
          $scope.total += item.unitPrice * item.quantity;
        });
        console.log("Success", resp);
      })
      .catch((error) => {
        console.log("Error", error);
      });
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
