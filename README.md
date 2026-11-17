Name: Haidar Wahyu Yasari
NIM: F1D02410114

## About the Program

A simple student dashboard built with Java Swing. The window has two tabs:

- **Home** — shows the total number of students that have been added.
- **Data** — a form to input a student's **Name** and **NIM**. Clicking **Tambah**
  (or pressing **Enter** in the NIM field) adds the student to the list below and
  updates the total on the Home tab. If a field is empty, a warning dialog appears.

## File Structure

| File | Description |
|---|---|
| `Main.java` | Entry point of the program |
| `DashboardFrame.java` | Main window (`JFrame`) that holds the title and the tabs |
| `HomePanel.java` | Home tab (`extends JPanel`), shows the total students |
| `DataPanel.java` | Data tab (`extends JPanel`), input form + student list + event handler |
| `Mahasiswa.java` | Model class that stores a student's name and NIM |

## How to Run

```bash
javac *.java
java Main
```

## Screenshots

**Home tab** — shows the total number of students.
<img width="864" height="686" alt="image" src="https://github.com/user-attachments/assets/256cc9cf-48d5-4584-92b9-fdfbfb2ac2c5" />


**Data tab** — input form and the list of added students.
<img width="870" height="682" alt="image" src="https://github.com/user-attachments/assets/b6ecf142-e9dc-4d13-b90e-c6f258aeb43c" />


**Validation** — a warning dialog appears when Name or NIM is empty.
<img width="868" height="680" alt="image" src="https://github.com/user-attachments/assets/39e2b4d3-f108-4b28-8552-594ca08ef024" />


---

## The 5 GUI Components / Parts

### 1. Top Level Container Class

**`JFrame`** — the main window of the application. Every other component is placed
inside it.

`DashboardFrame.java`
```java
private JFrame frame;

frame = new JFrame("Dashboard Mahasiswa");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(450, 350);
frame.setVisible(true);
```

### 2. Intermediate Container Class

Containers used to group other components inside the window.

| Class | File | Purpose |
|---|---|---|
| `JTabbedPane` | `DashboardFrame.java` | Holds the Home and Data tabs |
| `JPanel` (`HomePanel`) | `HomePanel.java` | Panel for the Home tab (`extends JPanel`) |
| `JPanel` (`DataPanel`) | `DataPanel.java` | Panel for the Data tab (`extends JPanel`) |
| `JPanel` (`form`) | `DataPanel.java` | Groups the labels, text fields and button |
| `JScrollPane` | `DataPanel.java` | Adds a scrollbar to the student list |

```java
tabs = new JTabbedPane();
tabs.addTab("Home", homePanel);
tabs.addTab("Data", dataPanel);
```
```java
JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));
add(new JScrollPane(txtOutput), BorderLayout.CENTER);
```

### 3. Atomic Component Class

Basic components that the user directly sees or interacts with.

| Class | Variable | File | Purpose |
|---|---|---|---|
| `JLabel` | `lblJudul` | `DashboardFrame.java` | Title of the dashboard |
| `JLabel` | `lblinfo`, `lblTotal` | `HomePanel.java` | "Total Mahasiswa" text and the total number |
| `JLabel` | `"Nama"`, `"NIM"` | `DataPanel.java` | Form labels |
| `JTextField` | `txtNama`, `txtNim` | `DataPanel.java` | Input for name and NIM |
| `JButton` | `btnTambah` | `DataPanel.java` | Button to add a student |
| `JTextArea` | `txtOutput` | `DataPanel.java` | Displays the list of students |

### 4. Layout Classes

Two layout classes are used:

**a. `BorderLayout`** — divides a container into NORTH, SOUTH, EAST, WEST and CENTER.
Used in `DashboardFrame`, `HomePanel` and `DataPanel`.

```java
frame.setLayout(new BorderLayout());
frame.add(lblJudul, BorderLayout.NORTH);   // title on top
frame.add(tabs, BorderLayout.CENTER);      // tabs fill the rest
```

**b. `GridLayout`** — arranges components in a grid where every cell has the same
size. Used for the input form in `DataPanel` (3 rows × 2 columns).

```java
JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));
form.add(new JLabel("Nama"));  form.add(txtNama);
form.add(new JLabel("NIM"));   form.add(txtNim);
form.add(new JLabel(""));      form.add(btnTambah);
```

### 5. Event Listener

The event is handled by an inner class `TambahHandler` that **implements the
`ActionListener` interface** and **overrides the `actionPerformed()` method**.

`DataPanel.java`
```java
private class TambahHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        tambahData();
    }
}
```

The same handler is registered to two event sources:

```java
TambahHandler handler = new TambahHandler();
btnTambah.addActionListener(handler);   // when the button is clicked
txtNim.addActionListener(handler);      // when Enter is pressed in the NIM field
```

When the event happens, `tambahData()`:
1. Reads the text from `txtNama` and `txtNim`.
2. Shows a `JOptionPane` warning if a field is empty.
3. Creates a new `Mahasiswa` object and stores it in an `ArrayList`.
4. Appends the student to `txtOutput`.
5. Updates the total on the Home tab with `homePanel.setTotal(daftar.size())`.

---

## Component Hierarchy

```
JFrame                                  (Top Level Container)  [BorderLayout]
├── JLabel lblJudul                     (Atomic)               NORTH
└── JTabbedPane tabs                    (Intermediate)         CENTER
    ├── HomePanel                       (Intermediate)         [BorderLayout]
    │   ├── JLabel lblinfo              (Atomic)
    │   └── JLabel lblTotal             (Atomic)
    └── DataPanel                       (Intermediate)         [BorderLayout]
        ├── JPanel form                 (Intermediate)         [GridLayout 3x2]
        │   ├── JLabel x3               (Atomic)
        │   ├── JTextField x2           (Atomic)  <- event source (txtNim)
        │   └── JButton btnTambah       (Atomic)  <- event source
        └── JScrollPane                 (Intermediate)
            └── JTextArea txtOutput     (Atomic)

TambahHandler implements ActionListener (Event Listener)
```
