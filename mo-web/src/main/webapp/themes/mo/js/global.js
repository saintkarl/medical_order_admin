/**
 * Created by khanhcq on 12-Oct-16.
 */

$(document).ready(function(){
    // === Tooltips === //
    $('.tip').tooltip();
    $('.tip-left').tooltip({ placement: 'left' });
    $('.tip-right').tooltip({ placement: 'right' });
    $('.tip-top').tooltip({ placement: 'top' });
    $('.tip-bottom').tooltip({ placement: 'bottom' });

    //==== Popover ===== //
    $('.popover-top').popover({
        html: true,
        trigger: 'hover',
        placement: 'top'
    });
    $('.ace_file_input').each(function(){
        var me = this;
        $(me).ace_file_input({
            style: 'well',
            btn_choose: $(me).attr('title'),
            btn_change: null,
            no_icon: 'ace-icon fa fa-cloud-upload',
            droppable: true,
            thumbnail: 'fit'
            ,
            preview_error : function(filename, error_code) {
            }

        });
    });
    if(!ace.vars['touch']) {
        $('.chosen-select').chosen();
        //resize the chosen on window resize
        $(window)
            .off('resize.chosen')
            .on('resize.chosen', function() {
                $('.chosen-select').each(function() {
                    var $this = $(this);
                    $this.next().css({'width': $this.parent().width()});
                })
            }).trigger('resize.chosen');
        //resize chosen on sidebar collapse/expand
        $(document).on('settings.ace.chosen', function(e, event_name, event_val) {
            if(event_name != 'sidebar_collapsed') return;
            $('.chosen-select').each(function() {
                var $this = $(this);
                $this.next().css({'width': $this.parent().width()});
            })
        });
    }
});

var buttonDeleteColor = "#DD6B55";

function deleteById(id, formId) {
    var form = 'itemForm';
    if (typeof formId != 'undefined') {
        form = formId;
    }
    $('#crudaction').val('delete');
    $('input[name="checkList"]').prop('checked', false);
    $('#hiddenItem').attr('name', 'checkList');
    $('#hiddenItem').val(id);
    $("#" + form).submit();
}

function deleteBySelectedIds(confirmData,callback){
    swal({
        title: confirmData.title,
        text: confirmData.text,
        type: "warning",
        showCancelButton: true,
        cancelButtonText: confirmData.cancelButtonText,
        confirmButtonColor: buttonDeleteColor,
        confirmButtonText: confirmData.confirmButtonText,
        closeOnConfirm: true
    }, callback);
}

function showWarningDelete(confirmData, id, form) {
    swal({
        title: confirmData.title,
        text: confirmData.text,
        type: "warning",
        showCancelButton: true,
        cancelButtonText: confirmData.cancelButtonText,
        confirmButtonColor: buttonDeleteColor,
        confirmButtonText: confirmData.confirmButtonText,
        closeOnConfirm: true
    }, function(){
        deleteById(id, form);
    });
}

function submitForm(formId){
    $('body').modalmanager('loading');
    $('#'+formId).submit();
}

function activeMenu(){
    var menu = $('meta[name=menu]').attr("content"),
        lv2menu = $('meta[name=lv2menu]').attr("content"),
        gmenu = $('meta[name=gmenu]').attr("content");
    $('#'+menu).addClass("active open");
    $('#'+lv2menu).addClass("active open");
    $('#'+gmenu).addClass("active");
}

function initDate(){
    if(!ace.vars['old_ie']) $('.date-picker').datetimepicker({
        format: 'DD/MM/YYYY',//use this option to display seconds
        icons: {
            time: 'fa fa-clock-o',
            date: 'fa fa-calendar',
            up: 'fa fa-chevron-up',
            down: 'fa fa-chevron-down',
            previous: 'fa fa-chevron-left',
            next: 'fa fa-chevron-right',
            today: 'fa fa-arrows ',
            clear: 'fa fa-trash',
            close: 'fa fa-times'
        }
    }).next().on(ace.click_event, function(){
            $(this).prev().focus();
        });
}

$(document).ready(function () {
    activeMenu();
    initDate();
    $('#checkAllItem').click(function () {
        if ($(this).prop('checked')) {
            $(this).closest('table').find('input[type="checkbox"]').prop('checked', true);
        } else {
            $(this).closest('table').find('input[type="checkbox"]').prop('checked', false);
        }
    });

    var active_class = 'active';
    $('.table-fcv-ace > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
        var th_checked = this.checked;//checkbox inside "TH" table header

        $(this).closest('table').find('tbody > tr').each(function(){
            var row = this;
            if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
            else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
        });
    });

    //select/deselect a row when the checkbox is checked/unchecked
    $('.table-fcv-ace').on('click', 'td input[type=checkbox]' , function(){
        var $row = $(this).closest('tr');
        if($row.is('.detail-row ')) return;
        if(this.checked) $row.addClass(active_class);
        else $row.removeClass(active_class);
    });

    $($('.tableTools-container')).find('a.dt-button').each(function() {
        var div = $(this).find(' > div').first();
        if(div.length == 1) div.tooltip({container: 'body', title: div.parent().text()});
        else $(this).tooltip({container: 'body', title: $(this).text()});
    });

});

