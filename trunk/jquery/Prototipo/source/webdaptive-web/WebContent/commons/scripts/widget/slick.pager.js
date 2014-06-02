(function ($)
{
  function SlickGridPager(loader, $container)
  {
    var $status;
	var pagesize = 0;
    var pagenum = 0;
    var totalRows = 0;

    function init()
	{
      constructPagerUI();
      updatePager(getPagingInfo());
    }

    function getNavState()
	{
      var pagingInfo = getPagingInfo();
      var lastPage = pagingInfo.totalPages - 1;
     return {
        canGotoFirst: pagingInfo.pageSize != 0 && pagingInfo.pageNum > 0,
        canGotoLast: pagingInfo.pageSize != 0 && pagingInfo.pageNum != lastPage,
        canGotoPrev: pagingInfo.pageSize != 0 && pagingInfo.pageNum > 0,
        canGotoNext: pagingInfo.pageSize != 0 && pagingInfo.pageNum < lastPage,
        pagingInfo: pagingInfo
      };
    }

    function setPageSize(n)
	{
       setPagingOptions({pageSize: n});
    }

    function getPagingInfo()
	{
      var totalPages = pagesize ? Math.max(1, Math.ceil(totalRows / pagesize)) : 1;
      return {pageSize: pagesize, pageNum: pagenum, totalRows: totalRows, totalPages: totalPages};
    }

    function setPagingOptions(args)
	{

      if (args.totalRows != undefined)
	  {
    	  totalRows = args.totalRows;
	  }

      if (args.pageSize != undefined)
	  {
    	  pagesize = args.pageSize;
      }

      if (args.pageNum != undefined)
	  {
    	  pagenum = args.pageNum;
      }

      if (args.doPage != undefined)
	  {
    	  loader.callPagedFetchWS(pagesize, pagenum);
      }
    }

    function gotoFirst()
	{
      if (getNavState().canGotoFirst)
	  {
        setPagingOptions({pageNum: 0, doPage: true});
      }
    }

    function gotoLast()
	{
      var state = getNavState();
      if (state.canGotoLast)
	  {
        setPagingOptions({pageNum: state.pagingInfo.totalPages - 1, doPage: true});
      }
    }

    function gotoPrev()
	{
      var state = getNavState();
      if (state.canGotoPrev)
	  {
        setPagingOptions({pageNum: state.pagingInfo.pageNum - 1, doPage: true});
      }
    }

    function gotoNext()
	{
      var state = getNavState();
      if (state.canGotoNext)
	  {
        setPagingOptions({pageNum: state.pagingInfo.pageNum + 1, doPage: true});
      }
    }

    function constructPagerUI()
	{
      $container.empty();

      var $nav = $("<span class='slick-pager-nav' />").appendTo($container);
      var $settings = $("<span class='slick-pager-settings' />").appendTo($container);
      $status = $("<span class='slick-pager-status' />").appendTo($container);

      var icon_prefix = "<span class='ui-state-default ui-corner-all ui-icon-container'><span class='ui-icon ";
      var icon_suffix = "' /></span>";

      $(icon_prefix + "ui-icon-seek-first" + icon_suffix)
          .click(gotoFirst)
          .appendTo($nav);

      $(icon_prefix + "ui-icon-seek-prev" + icon_suffix)
          .click(gotoPrev)
          .appendTo($nav);

      $(icon_prefix + "ui-icon-seek-next" + icon_suffix)
          .click(gotoNext)
          .appendTo($nav);

      $(icon_prefix + "ui-icon-seek-end" + icon_suffix)
          .click(gotoLast)
          .appendTo($nav);

      $container.find(".ui-icon-container")
          .hover(function () {
            $(this).toggleClass("ui-state-hover");
          });

      $container.children().wrapAll("<div class='slick-pager' />");
    }


    function updatePager(pagingInfo)
	{
      var state = getNavState();

      $container.find(".slick-pager-nav span").removeClass("ui-state-disabled");
      if (!state.canGotoFirst)
	  {
        $container.find(".ui-icon-seek-first").addClass("ui-state-disabled");
      }
      if (!state.canGotoLast)
	  {
        $container.find(".ui-icon-seek-end").addClass("ui-state-disabled");
      }
      if (!state.canGotoNext)
	  {
        $container.find(".ui-icon-seek-next").addClass("ui-state-disabled");
      }
      if (!state.canGotoPrev)
	  {
        $container.find(".ui-icon-seek-prev").addClass("ui-state-disabled");
      }

      $status.text("Showing page " + (pagingInfo.pageNum + 1) + " of " + (pagingInfo.totalPages) + "     - Totals Rows Available " + pagingInfo.totalRows);
    }

	return{
		"init"				: init,
		"updatePager"		: updatePager,
		"getPagingInfo"		: getPagingInfo,
		"setPagingOptions"	: setPagingOptions
	};
  }
  // Slick.Controls.Pager
  $.extend(true, window, { Slick:{ Controls:{ Pager:SlickGridPager }}});
})(jQuery);
