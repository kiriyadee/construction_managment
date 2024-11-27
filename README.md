## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

ผมจะอธิบายการทำงานของแต่ละ function ในโปรแกรม Construction Engineering ERP Software ครับ:
1. ฟังก์ชันหลัก (Main Functions)
)
- เป็นจุดเริ่มต้นของโปรแกรม
เริ่มด้วยการตรวจสอบการล็อกอิน (authentication)
แสดงเมนูหลักและรับคำสั่งจากผู้ใช้
มีตัวเลือก 6 เมนู: จัดการโครงการ, ซัพพลายเชน, การเงิน, ทรัพยากรบุคคล, รายงาน และออกจากระบบ
2. ระบบความปลอดภัย
)
- ตรวจสอบการล็อกอินด้วย username และ password
ให้โอกาสล็อกอิน 3 ครั้ง
ใช้ข้อมูลจาก CORRECT_CREDENTIALS เพื่อตรวจสอบ
คืนค่า true ถ้าล็อกอินสำเร็จ, false ถ้าล้มเหลว
3. การจัดการโครงการ
)
- แสดงเมนูย่อยสำหรับการจัดการโครงการ
มีฟังก์ชันย่อย:
addProject: เพิ่มโครงการใหม่
listProjects: แสดงรายการโครงการทั้งหมด
updateProjectStatus: อัพเดทสถานะโครงการ
4. การจัดการซัพพลายเชนและสินค้าคงคลัง
)
- จัดการเกี่ยวกับการจัดซื้อและสินค้าคงคลัง
มีฟังก์ชันย่อย:
sourceSolarPanels: จัดซื้อแผงโซล่าร์
sourceInverters: จัดซื้ออินเวอร์เตอร์
sourceOtherComponents: จัดซื้อส่วนประกอบอื่นๆ
manageInventory: จัดการสินค้าคงคลัง
5. การจัดการการเงิน
)
- จัดการด้านการเงินและงบประมาณ
มีฟังก์ชันย่อย:
setBudget: กำหนดงบประมาณ (60% ของต้นทุนโครงการ)
controlCosts: ควบคุมต้นทุน (80% ของต้นทุนโครงการ)
listProjectsWithBudget: แสดงรายการโครงการพร้อมงบประมาณ
6. การจัดการทรัพยากรบุคคล
)
- จัดการข้อมูลพนักงาน
มีฟังก์ชันย่อย:
recordEmployeeInformation: บันทึกข้อมูลพนักงาน
showEmployeesInformation: แสดงข้อมูลพนักงานทั้งหมด
7. การสร้างรายงาน
)
สร้างรายงานรวมทั้งหมด ประกอบด้วย:
รายงานโครงการ
รายงานงบประมาณ
รายงานสินค้าคงคลัง
รายงานพนักงาน
โครงสร้างข้อมูล
โปรแกรมใช้ Array 2 มิติในการเก็บข้อมูล:
projects[][]: เก็บข้อมูลโครงการ
budgetData[][]: เก็บข้อมูลงบประมาณ
employees[][]: เก็บข้อมูลพนักงาน
การคำนวณต้นทุนและงบประมาณ
ต้นทุนโครงการ = (ขนาดโครงการ kWp × 1000 × ค่าต่อวัตต์) × 10 + VAT 7%
งบประมาณ = 60% ของต้นทุนโครงการ
การควบคุมต้นทุน = 80% ของต้นทุนโครงการ
โปรแกรมนี้ถูกออกแบบมาเพื่อช่วยในการบริหารจัดการโครงการก่อสร้างโซล่าร์เซลล์แบบครบวงจร ตั้งแต่การวางแผนโครงการ การจัดซื้อ การควบคุมงบประมาณ ไปจนถึงการจัดการทรัพยากรบุคคล