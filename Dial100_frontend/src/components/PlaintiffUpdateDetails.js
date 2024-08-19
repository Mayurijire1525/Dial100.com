import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import '../styles/PlaintiffUpdateDetails.css';

function PlaintiffUpdateDetails() {
  const { complaintId } = useParams();
  const navigate = useNavigate();
  const [updates, setUpdates] = useState([]);

  useEffect(() => {
    if (complaintId) {
      fetchUpdates(complaintId);
    }
  }, [complaintId]);

  const fetchUpdates = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/api/updates/complaint/${id}`);
      const data = await response.json();
      setUpdates(data);
    } catch (error) {
      console.error("Error fetching updates:", error);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem('user');
    navigate('/');
  };

  const formatDate = (dateString) => {
    // Convert the date string to a Date object
    const date = new Date(dateString);
    // Format the date as a readable string
    return date.toLocaleDateString(); // Example: "8/16/2024"
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
            <a href="/" onClick={handleLogout}>Logout</a>
          </div>
        </div>

        <div className="main-content">
          <h2>Complaints Updates</h2>
          <div className="complaints-list">
            {updates.length > 0 ? (
              updates.map((update) => (
                <div key={update.updateId} className="complaint-item">
                  <h3>Update ID: {update.updateId}</h3>
                  <p><strong>Status:</strong> {update.status}</p>
                  <p><strong>Remarks:</strong> {update.remarks}</p>
                  <p><strong>Update Date:</strong> {formatDate(update.updateDate)}</p>
                </div>
              ))
            ) : (
              <p>No updates found for this complaint.</p>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

export default PlaintiffUpdateDetails;
