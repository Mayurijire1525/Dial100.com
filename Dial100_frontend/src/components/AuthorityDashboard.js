import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/AuthorityDashboard.css'; 

function AuthorityDashboard() {
  const navigate = useNavigate();

  // Function to handle logout
  const handleLogout = () => {
    // Clear localStorage
    localStorage.removeItem('user');

    // Redirect to home page
    navigate('/');
  };

  return (
    <div className="dashboard">
      <div className="side-nav">
        <div className="logo">Dial100</div>
        <ul>
          <li><a href="/all-new-complaints">All New Complaints</a></li>
          <li><a href="/my-investigations">My Investigations</a></li>
        </ul>
      </div>
      
      <div className="content-area">
        <div className="top-nav">
          <input type="text" className="search-bar" placeholder="Search here..." />
          <div className="profile-options">
            <a href="/profile">Your Profile</a>
            <a href="/" onClick={handleLogout}>Logout</a> {/* Updated to call handleLogout */}
          </div>
        </div>

        <div className="main-content">
          <h1>Authority Dashboard</h1>
          <p>Manage complaints and update their status.</p>
        </div>
      </div>
    </div>
  );
}

export default AuthorityDashboard;
