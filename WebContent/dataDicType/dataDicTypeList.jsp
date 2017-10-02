<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function dataDicTypeDelete(ddTypeId){
	if(confirm("确定要删除这条数据字典吗？")){
		$.post("dataDicType!delete",{ddTypeId:ddTypeId},
				function(result){
			    var result=eval('('+result+')');
			    if(result.error){
			    	alert(result.error);
			    }else{
			    	alert("删除成功");
			    	window.location.href="${pageContext.request.contextPath}/dataDicType!list";
			    }
		})
	}
}


</script>
</head>
<body>
<div class="data_list">
	<div class="search_content">
		<button class="btn btn-mini btn-primary" type="button" style="float:rigth;margin-botttom:5px;" onclick="javascript:window.location='dataDicType!preSave'">添加数据字典类别</button>
	</div>
	<div class="data_content">
		<table class="table table-bordered table-hover">
			<tr>
				<th>序号</th>
				<th>数据字典类别名称</th>
				<th>数据字典类别备注</th>
				<th>操作</th>
			</tr>
			<c:forEach var="dataDicType" items="${dataDicTypeList }" varStatus="status">
				<tr>
					<td>${status.index+1 }</td>
					<td>${dataDicType.ddTypeName }</td>
					<td>${dataDicType.ddTypeDesc }</td>
					<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='dataDicType!preSave?ddTypeId=${dataDicType.ddTypeId}'">修改</button>&nbsp;&nbsp;<button class="btn btn-mini btn-danger" type="button" onclick="dataDicTypeDelete(${dataDicType.ddTypeId})">删除</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
</body>
</html>