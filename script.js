// ===== SmartBank - script.js =====
// Same logic as the Java backend (Main, Bank, Customer, Account),
// but running in the browser. Data is saved in the browser (localStorage).

const $ = id => document.getElementById(id);

// ---------- DATA (like the ArrayList in Bank.java) ----------
let accounts = [];
let nextAccountNumber = 1001;
let currentAccount = null;   // the logged-in account

try {
  accounts = JSON.parse(localStorage.getItem("accounts")) || [];
  nextAccountNumber = Number(localStorage.getItem("nextAccNo")) || 1001;
} catch (e) { accounts = []; }

function saveData() {
  try {
    localStorage.setItem("accounts", JSON.stringify(accounts));
    localStorage.setItem("nextAccNo", nextAccountNumber);
  } catch (e) { }
}

// ---------- HELPERS ----------
const round2 = n => Math.round(n * 100) / 100;

function money(n) {
  return "₹" + n.toLocaleString("en-IN", { minimumFractionDigits: 2, maximumFractionDigits: 2 });
}

let toastTimer;
function toast(msg, type) {
  const t = $("toast");
  t.textContent = msg;
  t.className = "show " + type;
  clearTimeout(toastTimer);
  toastTimer = setTimeout(() => { t.className = ""; }, 2800);
}

function showScreen(id) {
  document.querySelectorAll(".screen").forEach(s => s.classList.remove("active"));
  $(id).classList.add("active");
  $("logoutBtn").classList.toggle("hidden", id !== "dashboard");
}

function openPanel(name) {
  document.querySelectorAll(".panel").forEach(p => p.classList.toggle("on", p.id === "panel-" + name));
  document.querySelectorAll(".tile").forEach(t => t.classList.toggle("on", t.dataset.panel === name));
}

// ===== MEMBER 1: Main Menu + Login =====
function login(e) {
  e.preventDefault();
  const accNo = Number($("loginAcc").value);
  const pin = Number($("loginPin").value);
  const acc = accounts.find(a => a.accountNumber === accNo && a.pin === pin);

  if (!acc) return toast("Login failed! Wrong account number or PIN.", "error");

  currentAccount = acc;
  $("loginForm").reset();
  refresh();
  openPanel("deposit");
  showScreen("dashboard");
  toast("Login successful! Welcome, " + acc.holderName, "success");
}

function logout() {
  currentAccount = null;
  showScreen("home");
  toast("Logged out successfully.", "success");
}

// ===== MEMBER 2: Create Account =====
function createAccount(e) {
  e.preventDefault();
  const name = $("newName").value.trim();
  const pin = Number($("newPin").value);
  const balance = Number($("newBalance").value);

  if (name === "") return toast("Name cannot be empty!", "error");
  if (!Number.isInteger(pin) || pin < 1000 || pin > 9999) return toast("PIN must be exactly 4 digits!", "error");
  if (balance < 0) return toast("Opening balance cannot be negative!", "error");

  accounts.push({ accountNumber: nextAccountNumber, holderName: name, pin: pin, balance: balance });
  $("createResult").textContent = "Account created! Your Account Number is " + nextAccountNumber + ". Remember it for login.";
  $("createResult").classList.remove("hidden");
  nextAccountNumber++;
  saveData();
  $("createForm").reset();
  toast("Account created successfully!", "success");
}

// ===== MEMBER 3: Deposit + Withdraw =====
function deposit(e) {
  e.preventDefault();
  const amount = Number($("depAmount").value);

  if (!(amount > 0)) return toast("Invalid amount! Must be greater than 0.", "error");

  currentAccount.balance = round2(currentAccount.balance + amount);
  saveData();
  refresh();
  $("depositForm").reset();
  toast(money(amount) + " deposited successfully.", "success");
}

function withdraw(e) {
  e.preventDefault();
  const amount = Number($("witAmount").value);

  if (!(amount > 0)) return toast("Invalid amount! Must be greater than 0.", "error");
  if (amount > currentAccount.balance) return toast("Insufficient balance!", "error");

  currentAccount.balance = round2(currentAccount.balance - amount);
  saveData();
  refresh();
  $("withdrawForm").reset();
  toast(money(amount) + " withdrawn successfully.", "success");
}

// ===== MEMBER 4: Check Balance + View Details =====
function refresh() {
  const a = currentAccount;
  $("welcomeText").textContent = "Welcome back, " + a.holderName.split(" ")[0] + " 👋";
  $("vName").textContent = a.holderName;
  $("vNum").textContent = "A/C " + a.accountNumber;
  $("vBal").textContent = money(a.balance);
  $("bigBal").textContent = money(a.balance);    // Check Balance
  $("dNum").textContent = a.accountNumber;       // View Details
  $("dName").textContent = a.holderName;
  $("dBal").textContent = money(a.balance);
}

// ---------- CONNECT BUTTONS / FORMS ----------
document.querySelectorAll("[data-go]").forEach(b => b.addEventListener("click", () => {
  $("createResult").classList.add("hidden");
  showScreen(b.dataset.go);
}));
document.querySelectorAll(".tile").forEach(t => t.addEventListener("click", () => openPanel(t.dataset.panel)));
$("createForm").addEventListener("submit", createAccount);
$("loginForm").addEventListener("submit", login);
$("depositForm").addEventListener("submit", deposit);
$("withdrawForm").addEventListener("submit", withdraw);
$("logoutBtn").addEventListener("click", logout);
