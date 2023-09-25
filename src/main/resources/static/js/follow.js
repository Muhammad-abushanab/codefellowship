document.addEventListener("DOMContentLoaded", function() {
    function updateFollowButton(username) {
      fetch('/check-follow/' + username)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.text();
      })
      .then(status => {
        var followButton = document.getElementById('follow');
        if (status === 'following') {
          followButton.textContent = 'Unfollow';
        } else if (status === 'notFollowing') {
          followButton.textContent = 'Follow';
        }
      })
      .catch(error => {
        console.error('Error:', error);
        // Handle errors
      });
    }
    var username = document.getElementById('follow').getAttribute('data-username');
    updateFollowButton(username);
  });
function followUser(element) {
    var username = element.getAttribute('data-username');
    var buttonText = element.textContent.trim();

    var url = buttonText === 'Follow' ? '/follow/' : '/unfollow/';
    url += username;

    fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      credentials: 'same-origin',
      body: JSON.stringify({ username: username })
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      // Update UI based on the updated button text
      if (buttonText === 'Follow') {
        element.textContent = 'Unfollow';
      } else if (buttonText === 'Unfollow') {
        element.textContent = 'Follow';
      }
    })
    .catch(error => {
      console.error('Error:', error);
      // Handle errors
    });
  }