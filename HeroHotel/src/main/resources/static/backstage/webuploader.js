<script type="text/javascript">
  2     var  imagePath=null; 
  3     
  4         function article_save() {
  5             alert("刷新父级的时候会自动关闭弹层。");
  6             window.parent.location.reload();
  7         }
  8 
  9         /*项目发票-添加*/
 10         function article_add(title, url, w, h) {
 11         if(imagePath){
 12             var index = layer.open({
 13                 type : 2,
 14                 title : title,
 15                 content : url+imagePath
 16             });
 17             layer.full(index);
 18         }else{alert("没有上传图片，请先上传图片");}
 19         }
 20         
 21         $(function() {
 22             $('.skin-minimal input').iCheck({
 23                 checkboxClass : 'icheckbox-blue',
 24                 radioClass : 'iradio-blue',
 25                 increaseArea : '20%'
 26             });
 27 
 28             $list = $("#fileList"), $btn = $("#btn-star"), state = "pending",
 29                     uploader;
 30 
 31             var uploader = WebUploader.create({
 32                 auto : true,
 33                 swf : 'lib/webuploader/0.1.5/Uploader.swf',// swf文件路径
 34 
 35                 // 文件接收服务端。
 36                 server : 'lib/webuploader/0.1.5/server/fileupload.php', // 文件接收服务端。
 37 
 38                 // 选择文件的按钮。可选。
 39                 // 内部根据当前运行是创建，可能是input元素，也可能是flash.
 40                 pick : '#filePicker',
 41 
 42                 // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
 43                 resize : false,
 44                 // 只允许选择图片文件。
 45                 accept : {
 46                     title : 'Images',
 47                     extensions : 'gif,jpg,jpeg,bmp,png',
 48                     mimeTypes : 'image/*'
 49                 }
 50             });
 51             // 当有文件被添加进队列的时候
 52             uploader
 53                     .on(
 54                             'fileQueued',
 55                             function(file) {
 56                                 var $li = $('<div id="' + file.id + '" class="item">'
 57                                         + '<div class="pic-box"><img></div>'
 58                                         + '<div class="info">'
 59                                         + file.name
 60                                         + '</div>'
 61                                         + '<p class="state">等待上传...</p>'
 62                                         + '</div>'), $img = $li.find('img');
 63                                 $list.append($li);
 64 
 65                                 // 创建缩略图
 66                                 // 如果为非图片文件，可以不用调用此方法。
 67                                 // thumbnailWidth x thumbnailHeight 为 100 x 100
 68                                 uploader.makeThumb(file, function(error, src) {
 69                                     if (error) {
 70                                         $img.replaceWith('<span>不能预览</span>');
 71                                         return;
 72                                     }
 73 
 74                                     $img.attr('src', src);
 75                                 }, thumbnailWidth, thumbnailHeight);
 76                             });
 77             // 文件上传过程中创建进度条实时显示。
 78             uploader
 79                     .on(
 80                             'uploadProgress',
 81                             function(file, percentage) {
 82                                 var $li = $('#' + file.id), $percent = $li
 83                                         .find('.progress-box .sr-only');
 84 
 85                                 // 避免重复创建
 86                                 if (!$percent.length) {
 87                                     $percent = $(
 88                                             '<div class="progress-box"><span class="progress-bar radius"><span class="sr-only" style="width:0%"></span></span></div>')
 89                                             .appendTo($li).find('.sr-only');
 90                                 }
 91                                 $li.find(".state").text("上传中");
 92                                 $percent.css('width', percentage * 100 + '%');
 93                             });
 94 
 95             // 文件上传成功，给item添加成功class, 用样式标记上传成功。
 96             uploader.on('uploadSuccess', function(file) {
 97                 $('#' + file.id).addClass('upload-state-success')
 98                         .find(".state").text("已上传");
 99             });
100 
101             // 文件上传失败，显示上传出错。
102             uploader.on('uploadError', function(file) {
103                 $('#' + file.id).addClass('upload-state-error').find(".state")
104                         .text("上传出错");
105             });
106 
107             // 完成上传完了，成功或者失败，先删除进度条。
108             uploader.on('uploadComplete', function(file) {
109                 $('#' + file.id).find('.progress-box').fadeOut();
110             });
111             uploader.on('all', function(type) {
112                 if (type === 'startUpload') {
113                     state = 'uploading';
114                 } else if (type === 'stopUpload') {
115                     state = 'paused';
116                 } else if (type === 'uploadFinished') {
117                     state = 'done';
118                 }
119 
120                 if (state === 'uploading') {
121                     $btn.text('暂停上传');
122                 } else {
123                     $btn.text('开始上传');
124                 }
125             });
126 
127             $btn.on('click', function() {
128                 if (state === 'uploading') {
129                     uploader.stop();
130                 } else {
131                     uploader.upload();
132                 }
133             });
134 
135         });
136 
137         (function($) {
138             // 当domReady的时候开始初始化
139             $(function() {
140                 var $wrap = $('.uploader-list-container'),
141 
142                 // 图片容器
143                 $queue = $('<ul class="filelist"></ul>').appendTo(
144                         $wrap.find('.queueList')),
145 
146                 // 状态栏，包括进度和控制按钮
147                 $statusBar = $wrap.find('.statusBar'),
148 
149                 // 文件总体选择信息。
150                 $info = $statusBar.find('.info'),
151 
152                 // 上传按钮
153                 $upload = $wrap.find('.uploadBtn'),
154 
155                 // 没选择文件之前的内容。
156                 $placeHolder = $wrap.find('.placeholder'),
157 
158                 $progress = $statusBar.find('.progress').hide(),
159 
160                 // 添加的文件数量
161                 fileCount = 0,
162 
163                 // 添加的文件总大小
164                 fileSize = 0,
165 
166                 // 优化retina, 在retina下这个值是2
167                 ratio = window.devicePixelRatio || 1,
168 
169                 // 缩略图大小
170                 thumbnailWidth = 110 * ratio, thumbnailHeight = 110 * ratio,
171 
172                 // 可能有pedding, ready, uploading, confirm, done.
173                 state = 'pedding',
174 
175                 // 所有文件的进度信息，key为file id
176                 percentages = {},
177                 // 判断浏览器是否支持图片的base64
178                 isSupportBase64 = (function() {
179                     var data = new Image();
180                     var support = true;
181                     data.onload = data.onerror = function() {
182                         if (this.width != 1 || this.height != 1) {
183                             support = false;
184                         }
185                     }
186                     data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
187                     return support;
188                 })(),
189 
190                 // 检测是否已经安装flash，检测flash的版本
191                 flashVersion = (function() {
192                     var version;
193 
194                     try {
195                         version = navigator.plugins['Shockwave Flash'];
196                         version = version.description;
197                     } catch (ex) {
198                         try {
199                             version = new ActiveXObject(
200                                     'ShockwaveFlash.ShockwaveFlash')
201                                     .GetVariable('$version');
202                         } catch (ex2) {
203                             version = '0.0';
204                         }
205                     }
206                     version = version.match(/\d+/g);
207                     return parseFloat(version[0] + '.' + version[1], 10);
208                 })(),
209 
210                 supportTransition = (function() {
211                     var s = document.createElement('p').style, r = 'transition' in s
212                             || 'WebkitTransition' in s
213                             || 'MozTransition' in s
214                             || 'msTransition' in s || 'OTransition' in s;
215                     s = null;
216                     return r;
217                 })(),
218 
219                 // WebUploader实例
220                 uploader;
221 
222                 if (!WebUploader.Uploader.support('flash')
223                         && WebUploader.browser.ie) {
224 
225                     // flash 安装了但是版本过低。
226                     if (flashVersion) {
227                         (function(container) {
228                             window['expressinstallcallback'] = function(state) {
229                                 switch (state) {
230                                 case 'Download.Cancelled':
231                                     alert('您取消了更新！')
232                                     break;
233 
234                                 case 'Download.Failed':
235                                     alert('安装失败')
236                                     break;
237 
238                                 default:
239                                     alert('安装已成功，请刷新！');
240                                     break;
241                                 }
242                                 delete window['expressinstallcallback'];
243                             };
244 
245                             var swf = 'expressInstall.swf';
246                             // insert flash object
247                             var html = '<object type="application/'
248                                     + 'x-shockwave-flash" data="' + swf + '" ';
249 
250                             if (WebUploader.browser.ie) {
251                                 html += 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ';
252                             }
253 
254                             html += 'width="100%" height="100%" style="outline:0">'
255                                     + '<param name="movie" value="' + swf + '" />'
256                                     + '<param name="wmode" value="transparent" />'
257                                     + '<param name="allowscriptaccess" value="always" />'
258                                     + '</object>';
259 
260                             container.html(html);
261 
262                         })($wrap);
263 
264                         // 压根就没有安转。
265                     } else {
266                         $wrap
267                                 .html('<a href="http://www.adobe.com/go/getflashplayer" target="_blank" border="0"><img alt="get flash player" src="http://www.adobe.com/macromedia/style_guide/images/160x41_Get_Flash_Player.jpg" /></a>');
268                     }
269 
270                     return;
271                 } else if (!WebUploader.Uploader.support()) {
272                     alert('Web Uploader 不支持您的浏览器！');
273                     return;
274                 }
275 
276                 // 实例化
277                 uploader = WebUploader.create({
278                     pick : {
279                         id : '#filePicker-2',
280                         label : '点击选择图片'
281                     },
282                     formData : {
283                         uid : 123
284                     },
285                     dnd : '#dndArea',
286                     paste : '#uploader',
287                     swf : 'lib/webuploader/0.1.5/Uploader.swf',
288                     chunked : false,
289                     chunkSize : 512 * 1024,
290                     server : 'invoiceVo/news_uploder?&projectId=${projectId}',
291                     // runtimeOrder: 'flash',
292 
293                     // accept: {
294                     //     title: 'Images',
295                     //     extensions: 'gif,jpg,jpeg,bmp,png',
296                     //     mimeTypes: 'image/*'
297                     // },
298 
299                     // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
300                     disableGlobalDnd : true,
301                     fileNumLimit : 300,
302                     fileSizeLimit : 200 * 1024 * 1024, // 200 M
303                     fileSingleSizeLimit : 50 * 1024 * 1024
304                 // 50 M
305                 });
306 
307                 // 拖拽时不接受 js, txt 文件。
308                 uploader.on('dndAccept', function(items) {
309                     var denied = false, len = items.length, i = 0,
310                     // 修改js类型
311                     unAllowed = 'text/plain;application/javascript ';
312 
313                     for (; i < len; i++) {
314                         // 如果在列表里面
315                         if (~unAllowed.indexOf(items[i].type)) {
316                             denied = true;
317                             break;
318                         }
319                     }
320 
321                     return !denied;
322                 });
323 
324                 uploader.on('dialogOpen', function() {
325                     console.log('here');
326                 });
327 
328                 // uploader.on('filesQueued', function() {
329                 //     uploader.sort(function( a, b ) {
330                 //         if ( a.name < b.name )
331                 //           return -1;
332                 //         if ( a.name > b.name )
333                 //           return 1;
334                 //         return 0;
335                 //     });
336                 // });
337 
338                 // 添加“添加文件”的按钮，
339 /*                 uploader.addButton({
340                     id : '#filePicker2',
341                     label : '继续添加'
342                 }); */
343 
344                 uploader.on('ready', function() {
345                     window.uploader = uploader;
346                 });
347 
348                 // 当有文件添加进来时执行，负责view的创建
349                 function addFile(file) {
350                     var $li = $('<li id="' + file.id + '">'
351                             + '<p class="title">' + file.name + '</p>'
352                             + '<p class="imgWrap"></p>'
353                             + '<p class="progress"><span></span></p>' + '</li>'),
354 
355                     $btns = $(
356                             '<div class="file-panel">'
357                                     + '<span class="cancel">删除</span>'
358                                     + '<span class="rotateRight">向右旋转</span>'
359                                     + '<span class="rotateLeft">向左旋转</span></div>')
360                             .appendTo($li), $prgress = $li
361                             .find('p.progress span'), $wrap = $li
362                             .find('p.imgWrap'), $info = $('<p class="error"></p>'),
363 
364                     showError = function(code) {
365                         switch (code) {
366                         case 'exceed_size':
367                             text = '文件大小超出';
368                             break;
369 
370                         case 'interrupt':
371                             text = '上传暂停';
372                             break;
373 
374                         default:
375                             text = '上传失败，请重试';
376                             break;
377                         }
378 
379                         $info.text(text).appendTo($li);
380                     };
381 
382                     if (file.getStatus() === 'invalid') {
383                         showError(file.statusText);
384                     } else {
385                         // @todo lazyload
386                         $wrap.text('预览中');
387                         uploader
388                                 .makeThumb(
389                                         file,
390                                         function(error, src) {
391                                             var img;
392 
393                                             if (error) {
394                                                 $wrap.text('不能预览');
395                                                 return;
396                                             }
397 
398                                             if (isSupportBase64) {
399                                                 img = $('<img src="'+src+'">');
400                                                 $wrap.empty().append(img);
401                                             } else {
402                                                 $
403                                                         .ajax(
404                                                                 'lib/webuploader/0.1.5/server/preview.php',
405                                                                 {
406                                                                     method : 'POST',
407                                                                     data : src,
408                                                                     dataType : 'json'
409                                                                 })
410                                                         .done(
411                                                                 function(
412                                                                         response) {
413                                                                     if (response.result) {
414                                                                         img = $('<img src="'+response.result+'">');
415                                                                         $wrap
416                                                                                 .empty()
417                                                                                 .append(
418                                                                                         img);
419                                                                     } else {
420                                                                         $wrap
421                                                                                 .text("预览出错");
422                                                                     }
423                                                                 });
424                                             }
425                                         }, thumbnailWidth, thumbnailHeight);
426 
427                         percentages[file.id] = [ file.size, 0 ];
428                         file.rotation = 0;
429                     }
430 
431                     file.on('statuschange', function(cur, prev) {
432                         if (prev === 'progress') {
433                             $prgress.hide().width(0);
434                         } else if (prev === 'queued') {
435                             $li.off('mouseenter mouseleave');
436                             $btns.remove();
437                         }
438 
439                         // 成功
440                         if (cur === 'error' || cur === 'invalid') {
441                             console.log(file.statusText);
442                             showError(file.statusText);
443                             percentages[file.id][1] = 1;
444                         } else if (cur === 'interrupt') {
445                             showError('interrupt');
446                         } else if (cur === 'queued') {
447                             percentages[file.id][1] = 0;
448                         } else if (cur === 'progress') {
449                             $info.remove();
450                             $prgress.css('display', 'block');
451                         } else if (cur === 'complete') {
452                             $li.append('<span class="success"></span>');
453                         }
454 
455                         $li.removeClass('state-' + prev).addClass(
456                                 'state-' + cur);
457                     });
458 
459                     $li.on('mouseenter', function() {
460                         $btns.stop().animate({
461                             height : 30
462                         });
463                     });
464 
465                     $li.on('mouseleave', function() {
466                         $btns.stop().animate({
467                             height : 0
468                         });
469                     });
470 
471                     $btns
472                             .on(
473                                     'click',
474                                     'span',
475                                     function() {
476                                         var index = $(this).index(), deg;
477 
478                                         switch (index) {
479                                         case 0:
480                                             uploader.removeFile(file);
481                                             return;
482 
483                                         case 1:
484                                             file.rotation += 90;
485                                             break;
486 
487                                         case 2:
488                                             file.rotation -= 90;
489                                             break;
490                                         }
491 
492                                         if (supportTransition) {
493                                             deg = 'rotate(' + file.rotation
494                                                     + 'deg)';
495                                             $wrap.css({
496                                                 '-webkit-transform' : deg,
497                                                 '-mos-transform' : deg,
498                                                 '-o-transform' : deg,
499                                                 'transform' : deg
500                                             });
501                                         } else {
502                                             $wrap
503                                                     .css(
504                                                             'filter',
505                                                             'progid:DXImageTransform.Microsoft.BasicImage(rotation='
506                                                                     + (~~((file.rotation / 90) % 4 + 4) % 4)
507                                                                     + ')');
508 
509                                         }
510 
511                                     });
512 
513                     $li.appendTo($queue);
514                 }
515 
516                 // 负责view的销毁
517                 function removeFile(file) {
518                     var $li = $('#' + file.id);
519 
520                     delete percentages[file.id];
521                     updateTotalProgress();
522                     $li.off().find('.file-panel').off().end().remove();
523                 }
524 
525                 function updateTotalProgress() {
526                     var loaded = 0, total = 0, spans = $progress.children(), percent;
527 
528                     $.each(percentages, function(k, v) {
529                         total += v[0];
530                         loaded += v[0] * v[1];
531                     });
532 
533                     percent = total ? loaded / total : 0;
534 
535                     spans.eq(0).text(Math.round(percent * 100) + '%');
536                     spans.eq(1).css('width', Math.round(percent * 100) + '%');
537                     updateStatus();
538                 }
539 
540                 function updateStatus() {
541                     var text = '', stats;
542 
543                     if (state === 'ready') {
544                         text = '选中' + fileCount + '张图片，共'
545                                 + WebUploader.formatSize(fileSize) + '。';
546                     } else if (state === 'confirm') {
547                         stats = uploader.getStats();
548                         if (stats.uploadFailNum) {
549                             text = '已成功上传'
550                                     + stats.successNum
551                                     + '张照片至XX相册，'
552                                     + stats.uploadFailNum
553                                     + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
554                         }
555 
556                     } else {
557                         stats = uploader.getStats();
558                         text = '共' + fileCount + '张（'
559                                 + WebUploader.formatSize(fileSize) + '），已上传'
560                                 + stats.successNum + '张';
561 
562                         if (stats.uploadFailNum) {
563                             text += '，失败' + stats.uploadFailNum + '张';
564                         }
565                     }
566 
567                     $info.html(text);
568                 }
569 
570                 function setState(val) {
571                     var file, stats;
572 
573                     if (val === state) {
574                         return;
575                     }
576 
577                     $upload.removeClass('state-' + state);
578                     $upload.addClass('state-' + val);
579                     state = val;
580 
581                     switch (state) {
582                     case 'pedding':
583                         $placeHolder.removeClass('element-invisible');
584                         $queue.hide();
585                         $statusBar.addClass('element-invisible');
586                         uploader.refresh();
587                         break;
588 
589                     case 'ready':
590                         $placeHolder.addClass('element-invisible');
591                         $('#filePicker2').removeClass('element-invisible');
592                         $queue.show();
593                         $statusBar.removeClass('element-invisible');
594                         uploader.refresh();
595                         break;
596 
597                     case 'uploading':
598                         $('#filePicker2').addClass('element-invisible');
599                         $progress.show();
600                         $upload.text('暂停上传');
601                         break;
602 
603                     case 'paused':
604                         $progress.show();
605                         $upload.text('继续上传');
606                         break;
607 
608                     case 'confirm':
609                         $progress.hide();
610                         $('#filePicker2').removeClass('element-invisible');
611                         $upload.text('开始上传');
612 
613                         stats = uploader.getStats();
614                         if (stats.successNum && !stats.uploadFailNum) {
615                             setState('finish');
616                             return;
617                         }
618                         break;
619                     case 'finish':
620                         stats = uploader.getStats();
621                         if (stats.successNum) {
622                             alert('上传成功');
623 
624                         } else {
625                             // 没有成功的图片，重设
626                             state = 'done';
627                             location.reload();
628                         }
629                         break;
630                     }
631 
632                     updateStatus();
633                 }
634 
635                 uploader.onUploadProgress = function(file, percentage) {
636                     var $li = $('#' + file.id), $percent = $li
637                             .find('.progress span');
638 
639                     $percent.css('width', percentage * 100 + '%');
640                     percentages[file.id][1] = percentage;
641                     updateTotalProgress();
642                 };
643 
644                 uploader.onFileQueued = function(file) {
645                     fileCount++;
646                     fileSize += file.size;
647 
648                     if (fileCount === 1) {
649                         $placeHolder.addClass('element-invisible');
650                         $statusBar.show();
651                     }
652 
653                     addFile(file);
654                     setState('ready');
655                     updateTotalProgress();
656                 };
657 
658                 uploader.onFileDequeued = function(file) {
659                     fileCount--;
660                     fileSize -= file.size;
661 
662                     if (!fileCount) {
663                         setState('pedding');
664                     }
665 
666                     removeFile(file);
667                     updateTotalProgress();
668 
669                 };
670 
671                 /*获取服务器返回的数据*/
672                 uploader.on('uploadSuccess', function(file, response) {
673                     alert(response.status);
674                     if (response.status == "success") {
675                         imagePath =response.filePath;
676                         alert("文件保存路径：" + response.filePath);
677                     } else if (response.status == "error")
678                         alert(response.error);
679                 });
680 
681                 uploader.on('all', function(type) {
682                     var stats;
683                     //alert(type);
684                     switch (type) {
685                     case 'uploadFinished':
686 
687                         setState('confirm');
688                         break;
689 
690                     case 'startUpload':
691                         setState('uploading');
692                         break;
693 
694                     case 'stopUpload':
695                         setState('paused');
696                         break;
697 
698                     }
699                 });
700 
701                 uploader.onError = function(code) {
702                     alert('Eroor: ' + code);
703                 };
704 
705                 $upload.on('click', function() {
706                     if ($(this).hasClass('disabled')) {
707                         return false;
708                     }
709 
710                     if (state === 'ready') { //点击上传按钮，进入准备状态。
711                         uploader.upload(); //开始上传
712                     } else if (state === 'paused') {
713                         uploader.upload();
714                     } else if (state === 'uploading') {
715                         uploader.stop();
716                     }
717                 });
718 
719                 $info.on('click', '.retry', function() {
720                     uploader.retry();
721                 });
722 
723                 $info.on('click', '.ignore', function() {
724                     alert('todo');
725                 });
726 
727                 $upload.addClass('state-' + state);
728                 updateTotalProgress();
729             });
730 
731         })(jQuery);
732     </script>