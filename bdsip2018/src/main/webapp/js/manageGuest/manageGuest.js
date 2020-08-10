// 本地使用 /bdsip2018/guest/addGuest.do
// 服务器使用 /guest/addGuest.do
var guestUrl = "/guest/getGuestList.do";

$(function() {
			$.ajax({
				url: guestUrl,
				dataType: 'json',
				type: 'POST',
				async: false,
				success: function(data) {
					/* 内容自定义 */
					var html = '';
					//returnGet必须是后台返回的参数名
					var datas = data.data;
					if(datas.length > 0) {
						for(var i = 0; i < datas.length; i++) {
							html += '<tr>' +
								'<td class="list list1">' + datas[i].id + '</td>' +
								'<td class="list list2">' + datas[i].name + '</td>' +
								'<td class="list list3">' + datas[i].gender + '</td>' +
								'<td class="list list4">' + datas[i].phone + '</td>' +
								'<td class="list list5">' + datas[i].email + '</td>' +
								'<td class="list list6">' + datas[i].company + '</td>' +
								'<td class="list list7">' + datas[i].job + '</td>' +
								'<td class="list list8">' + datas[i].jobTitle + '</td>'+
								'<td class="list list8">' + datas[i].createTime + '</td>';
							html += '</tr>'
							$('.table tbody').empty().append(html)
						}
					} else {
						html += '<tr>' +
							'<td colspan = "9">暂无数据！</td></tr>'
						$('.table tbody').empty().append(html)
					}
				},
				error: function() {
					$.common.popLoadClose();
					var html = '';
					html += '<tr>' +
						'<td colspan = "9">暂无数据！</td></tr>'
					$('.table tbody').empty().append(html)
				}
			})

		});