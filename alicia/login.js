// Function to update the navigation bar items based on login status
function updateNavigationItems() {
    const isLoggedIn = localStorage.getItem('loggedIn') === 'true';
    const myOrdersNavItem = document.getElementById('myOrdersNavItem');
    const shoppingCartNavItem = document.getElementById('shoppingCartNavItem');

    myOrdersNavItem.style.display = isLoggedIn ? 'block' : 'none';
    shoppingCartNavItem.style.display = isLoggedIn ? 'block' : 'none';
}

// Function to update dropdown items based on login status
function updateDropdownItems() {
    const isLoggedIn = localStorage.getItem('loggedIn') === 'true';
    const dropdownMenu = document.querySelector('.dropdown-menu');

    dropdownMenu.innerHTML = isLoggedIn ?
        `<li><a class="dropdown-item" href="member.html">會員資料</a></li>
        <li><button class="dropdown-item" onclick="logout()">登出</button></li>` :
        `<li><a class="dropdown-item" href="login.html">登入</a></li>`;
}

// Function to handle logout
function logout() {
    localStorage.setItem('loggedIn', 'false');
    window.location.href = 'index.html';
}

document.addEventListener('DOMContentLoaded', function () {
    updateDropdownItems();
    updateNavigationItems();
});
