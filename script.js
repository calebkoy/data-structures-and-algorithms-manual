$(document).ready(function() {
  $('.copy-code-button').click(function() {
    let clickedButton = $(this);
    let clickedButtonText = clickedButton.text();
    let clickedButtonId = clickedButton.attr('id');    
    let clipboard = new ClipboardJS('#' + clickedButtonId);
    clipboard.on('success', function(e) {
      e.clearSelection();
      clickedButton.text('Copied!');
      setTimeout(function() {
        clickedButton.text(clickedButtonText);
      }, 2000);
    });
    clipboard.on('error', function(e) {      
      clickedButton.text('Error: press Ctrl + C to copy');
      setTimeout(function() {
        clickedButton.text(clickedButtonText);
      }, 2000);
    });
  });

  // Explicitly set document base URL 
  // to prevent incorrect routing on GitHub pages
  let baseHref = "/data-structures-and-algorithms/";
  $('base').attr("href", baseHref);
});