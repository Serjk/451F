jQuery.fn.extend({
    disableSelection : function() {
        this.each(function() {
            this.onselectstart = function() { return false; };
            this.unselectable = "on";
            jQuery(this).css({
                '-moz-user-select': 'none',
                '-khtml-user-select': 'none',
                '-webkit-user-select': 'none',
                '-o-user-select': 'none',
                'user-select': 'none'
            });

        });
    },
    enableSelection : function() {
        this.each(function() {
            this.onselectstart = function() {};
            this.unselectable = "off";
            jQuery(this).css({
                '-moz-user-select': 'auto',
                '-khtml-user-select': 'auto',
                '-webkit-user-select': 'auto',
                '-o-user-select': 'auto',
                'user-select': 'auto'
            });
        });
    }
});