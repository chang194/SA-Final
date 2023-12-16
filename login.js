
// Function to update the navigation bar items based on login status
function updateNavigationItems() {
    const isLoggedIn = localStorage.getItem('loggedIn') === 'true';
    const myOrdersNavItem = document.getElementById('myOrdersNavItem');
    const shoppingCartNavItem = document.getElementById('shoppingCartNavItem');

    if (isLoggedIn) {
        myOrdersNavItem.style.display = 'block';
        shoppingCartNavItem.style.display = 'block';
    } else {
        myOrdersNavItem.style.display = 'none';
        shoppingCartNavItem.style.display = 'none';
    }
}

// Function to update dropdown items based on login status
function updateDropdownItems() {
    const isLoggedIn = localStorage.getItem('loggedIn') === 'true';
    const dropdownMenu = document.querySelector('.dropdown-menu');

    if (isLoggedIn) {
        dropdownMenu.innerHTML = `
      <li><a class="dropdown-item" href="member.html">會員資料</a></li>
      <li><button class="dropdown-item" onclick="logout()">登出</button></li>
    `;
    } else {
        dropdownMenu.innerHTML = `
      <li><a class="dropdown-item" href="login.html">登入</a></li>
    `;
    }
}

// Function to handle logout
function logout() {
    // Clear the login status
    localStorage.setItem('loggedIn', 'false');
    // Redirect to the homepage or wherever you need after logout
    window.location.href = 'index.html';
}

updateDropdownItems();
updateNavigationItems();