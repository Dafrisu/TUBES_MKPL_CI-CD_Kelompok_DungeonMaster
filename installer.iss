[Setup]
AppName=Dungeon Master
AppVersion=0.0.0
DefaultDirName={pf}\Dungeon Master
DefaultGroupName=Dungeon Master
OutputDir=Output
OutputBaseFilename=DungeonMasterInstaller
Compression=lzma
SolidCompression=yes

[Files]
Source: "DungeonMaster.exe"; DestDir: "{app}"; Flags: ignoreversion

[Icons]
Name: "{group}\Dungeon Master"; Filename: "{app}\DungeonMaster.exe"
Name: "{group}\Uninstall Dungeon Master"; Filename: "{uninstallexe}"
