// MyInvestigations.js
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/MyInvestigations.css';

function MyInvestigations() {
  const navigate = useNavigate();
  const [investigations, setInvestigations] = useState([]);

  useEffect(() => {
    fetchInvestigations();
  }, []);

  const fetchInvestigations = async () => {
    try {
      const userData = JSON.parse(localStorage.getItem('user'));

      if (!userData || !userData.userId) {
        alert('User is not logged in or user ID is missing');
        navigate('/');
        return;
      }

      // Fetch investigations based on userId
      const response = await fetch(`http://localhost:8080/api/investigations/authority/${userData.userId}`);
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const data = await response.json();

      // Assuming the API returns a list of investigations directly
      setInvestigations(data); // Set data directly, no need for .investigations
    } catch (error) {
      console.error('Error fetching investigations:', error);
    }
  };

  // Function to handle logout
  const handleLogout = () => {
    localStorage.removeItem('user');
    navigate('/');
  };

  // Function to handle view details click
  const handleViewDetails = (investigationId) => {
    navigate(`/investigationdetails/${investigationId}`);
  };

  // Function to handle add updates click
  const handleAddUpdates = (investigationId) => {
    navigate(`/addupdatesform/${investigationId}`);
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
            <a href="/" onClick={handleLogout}>Logout</a>
          </div>
        </div>

        <div className="main-content">
          <h1>All Investigations:</h1>
          <table className="investigations-table">
            <thead>
              <tr>
                <th>Investigation ID</th>
                <th>Details</th>
                <th>Updates</th>
              </tr>
            </thead>
            <tbody>
              {investigations.map((investigation) => (
                <tr key={investigation.investigationId}>
                  <td>{investigation.investigationId}</td>
                  <td><button onClick={() => handleViewDetails(investigation.investigationId)}>View</button></td>
                  <td><button onClick={() => handleAddUpdates(investigation.investigationId)}>Add</button></td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default MyInvestigations;
