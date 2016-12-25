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

function submitForm(formId){
    $('body').modalmanager('loading');
    $('#'+formId).submit();
}
