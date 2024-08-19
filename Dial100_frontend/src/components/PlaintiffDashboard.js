import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/PlaintiffDashboard.css';

function PlaintiffDashboard() {
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
          <li><a href="/register-complaint">Register Complaint</a></li>
          <li><a href="/view-complaints">View Complaints</a></li>
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
          <h1>Plaintiff Dashboard</h1>
          <p>Welcome to the Plaintiff Dashboard. Here you can register complaints and view their status.</p>
        </div>
      </div>
    </div>
  );
}

export default PlaintiffDashboard;
