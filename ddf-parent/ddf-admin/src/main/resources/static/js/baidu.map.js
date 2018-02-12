var map = new BMap.Map("container");
// 创建地图实例
map.centerAndZoom("上海",12);
map.enableScrollWheelZoom(true);
// 初始化地图，设置中心点坐标和地图级别
var geoc = new BMap.Geocoder();

